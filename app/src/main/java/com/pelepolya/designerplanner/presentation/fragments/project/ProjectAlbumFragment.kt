package com.pelepolya.designerplanner.presentation.fragments.project

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pelepolya.designerplanner.databinding.FragmentProjectAlbumBinding


class ProjectAlbumFragment : Fragment() {

    private var _binding: FragmentProjectAlbumBinding? = null
    private val binding get() = _binding!!

    private val GALLERY_REQ_CODE = 1000
    private val CAMERA_REQ_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGallery.setOnClickListener(View.OnClickListener {
            val iGallery = Intent(Intent.ACTION_PICK)
            iGallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(iGallery, GALLERY_REQ_CODE)
        })

        binding.btnCamera.setOnClickListener(View.OnClickListener {
            val iCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(iCamera, CAMERA_REQ_CODE)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            if (requestCode == GALLERY_REQ_CODE) {
                // for gallery

                binding.imgGallery.setImageURI(data?.data);
            }
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQ_CODE) {
                // for camera
                val img = data!!.extras!!["data"] as Bitmap?
                binding.imgCamera.setImageBitmap(img)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}