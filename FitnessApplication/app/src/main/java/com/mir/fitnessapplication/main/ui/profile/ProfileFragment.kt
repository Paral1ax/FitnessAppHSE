package com.mir.fitnessapplication.main.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.entry.EntryActivity
import com.mir.fitnessapplication.entry.ui.login.LoginActivity
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.main.MainActivity
import com.mir.fitnessapplication.main.ui.messenger.MessengerViewModel
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {
    private lateinit var messengerViewModel: MessengerViewModel
    private var exitButton: Button? = null
    private var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    var storageReference: StorageReference? = null

    private var image: CircleImageView? = null
    private var phoneTop  : TextView? = null
    private var nameSurname  : TextView? = null
    private var settingsPhone  : TextView? = null
    private var settingsUsername  : TextView? = null
    private var settingsNameSurname  : TextView? = null
    private var settingAboutMe  : TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messengerViewModel =
            ViewModelProvider(this).get(MessengerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        messengerViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitButton = view.findViewById(R.id.button2)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(FirebaseURL.DATABASE_URL)
        databaseReference = database!!.getReference("UserData")
        storageReference = FirebaseStorage.getInstance().reference
        image = view.findViewById(R.id.profile_image)
        phoneTop = view.findViewById(R.id.profile_phone_number)
        nameSurname = view.findViewById(R.id.profile_name)
        settingsUsername = view.findViewById(R.id.settings_username)
        settingsPhone = view.findViewById(R.id.settings_phone_number)
        settingsNameSurname = view.findViewById(R.id.settings_name_surname)
        settingAboutMe = view.findViewById(R.id.settings_about_me)
        exitButton?.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, EntryActivity::class.java))
            (context as MainActivity).finish()
        }

        image?.setOnClickListener {
            pickPhoto()
        }
        updateUI()
    }

    private fun changeUserPhoto() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(activity as MainActivity)
    }

    fun pickPhoto() {
        val documentsIntent = Intent(Intent.ACTION_GET_CONTENT)
        documentsIntent.type = "image/*"

        val otherGalleriesIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        otherGalleriesIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(
            documentsIntent,
            getString(R.string.pick_image_intent_chooser_title)
        ).putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(otherGalleriesIntent))

        startActivityForResult(
            chooserIntent,
            CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
        )
    }

    private fun updateUI() {
        val data = LoginActivity.userdata
        (context as AppCompatActivity).runOnUiThread{
            if (data != null) {

                image!!.downloadImage(data.profilePicture)
                phoneTop!!.text = data.phoneNumber
                nameSurname!!.text = data.name
                settingsUsername!!.text = data.username
                settingsPhone!!.text = data.phoneNumber
                settingsNameSurname!!.text = data.name
                    //settingAboutMe
            }
        }

    }

    fun CircleImageView.downloadImage(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.user_profile_pic)
            .into(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode){
                CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE -> {
                    val uri = data?.data
                    //this is written from a fragment.
                    CropImage.activity(uri).start(requireContext(), this@ProfileFragment)
                }
                CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE->{
                    val uri = (CropImage.getActivityResult(data) as CropImage.ActivityResult).uri
                    Thread {
                        val path = storageReference?.child(FirebaseURL.FOLDER_PROFILE_IMAGE)
                            ?.child(auth?.uid!!)
                        path!!.putFile(uri).addOnCompleteListener { task1 ->
                            if (task1.isSuccessful) {
                                path.downloadUrl.addOnCompleteListener { task1 ->
                                    if (task1.isSuccessful) {
                                        val photoUrl = task1.result.toString()
                                        database?.reference!!.child("UserData").child(auth?.uid!!)
                                            .child("profilePicture").setValue(photoUrl).addOnCompleteListener {
                                                if (it.isSuccessful) {
                                                    (this.context as AppCompatActivity).runOnUiThread {
                                                        Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                    image!!.downloadImage(photoUrl)
                                                    LoginActivity.userdata!!.profilePicture = photoUrl
                                                }
                                            }
                                    }
                                }
                            }
                        }
                    }.start()
                }
            }
        }
    }


}