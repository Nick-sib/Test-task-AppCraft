package com.nick_sib.testtaskappcraft.ui.fragments


import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.FragmentNavigationBinding
import com.nick_sib.testtaskappcraft.mvp.model.entity.LocationMessage
import com.nick_sib.testtaskappcraft.receiver.LocationReceiver
import com.nick_sib.testtaskappcraft.service.*

class ServicesFragment: Fragment() {

    private var binding: FragmentNavigationBinding? = null

    private var isPlaying: Boolean = false
    private var locationReceiver: LocationReceiver? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = FragmentNavigationBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initService()
        registerReceivers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeReceivers()
    }

    private fun initService() {
        isPlaying = isServiceRunning(NavMusicService::class.java, requireContext())
        if (isPlaying) showServiceRunning()
        else showServiceStopped()
        binding?.run {
            button.setOnClickListener {
                if (isPlaying) {
                    stopService()
                }
                else {
                    startService()
                }
            }
        }
    }

    private fun registerReceivers() {
        locationReceiver = LocationReceiver { showLocation(it) }.apply {
            register(requireContext())
        }
    }

    private fun removeReceivers() {
        locationReceiver?.unregister(requireContext())
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


    private fun stopService() {
        val serviceIntent = Intent(requireActivity(), NavMusicService::class.java)
        requireActivity().stopService(serviceIntent)
        isPlaying = false
        showServiceStopped()
    }

    private fun startService() {
        when {
            !isPermissionGranted(requireActivity()) -> {
                activity?.run {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        REQUEST_PERMISSION_LOCATION
                    )
                }
            }
            !isLocationEnabled(requireContext()) -> enableLocation(requireContext())

            else -> {
                val serviceIntent = Intent(requireActivity(), NavMusicService::class.java)
                ContextCompat.startForegroundService(requireActivity(), serviceIntent)
                isPlaying = true
                showServiceRunning()
            }
        }
    }

    private fun showLocation(data: LocationMessage) {
        binding?.run {
            textView.text = "${resources.getString(R.string.coordinates)}:\n lat:${data.latitude} \n lon:${data.longitude}"
        }
    }

    private fun showServiceRunning() {
        binding?.run {
            button.setText(R.string.s_stop)
        }
    }

    private fun showServiceStopped() {
        binding?.run {
            button.setText(R.string.s_start)
        }
    }

    private fun isServiceRunning(serviceClass: Class<*>, context: Context): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        @Suppress("DEPRECATION")
        manager?.also {
            for (service in it.getRunningServices(Int.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
        }
        return false
    }

}