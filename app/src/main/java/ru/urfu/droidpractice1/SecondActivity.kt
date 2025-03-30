package ru.urfu.droidpractice1

import android.content.Intent
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bumptech.glide.Glide
import ru.urfu.droidpractice1.MainActivity.Companion.LOG
import ru.urfu.droidpractice1.databinding.ActivitySecondBinding

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var read by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        read = intent.getBooleanExtra(READ, false)
        initializeUi()

        Log.e(LOG, "${this.componentName.className} onCreate")
    }

    private fun initializeUi(){
        Glide
            .with(binding.imageview)
            .asBitmap()
            .load("https://kinopoisk-ru.clstorage.net/30T1GV336/ebc415uj01hc/eNg47wekw5MIkoCqDMxGZQRhvAYMpe8RZaANCDh4iYONyS4AJ7bmA8s1SQqcAgv66Oua7kcL_rphELYa0rMt6SwdXMrmwQbyCKNFP8mMMuTR4b0HqeFLYLARGK2ce4rgr2_dYgSEBCWswT0enfee_4zvCn6Tax9eUAeQy1VQKnw4GwtfkGYi4Eyj0elO30UCthDtIgxnXucEgY97ibl7Qd1M7dPldiTkfLENLOqHP17PbTjO4mnq7tH0wH-WUFpOm-qozIGUUVAP8VCKrG13E7bBX_HORQ0WlIH_ynn5SHD9PW3D57R2p96yvj8rxe7NPn4qbjDrCa-RFDMtxgPYnsq4qFzB9TKALOPTyZ6_VvOlII7gPfX8dtYx_Nn7WyoA7-3ectakdnfvIVzuq2aOfZ8OS4wz2wiv8TdyL5Zyawzq6qquEFbzoE6CExqu_SfS1nJNAezlndfGAt64GGpZ8o8Pr-IVVFVH3oAdz5hXPD59XLkuMQuZnbH2IczGMQvNGIsYr6LnQvLfA5IavH9EYXezXtOsJB0E1HLu22uL2ZAtz0_CZSelJi0DTp0bpz0-bYzIHxHJGc0zp8EMN3N6fDlLCs1j1SPybuOhi-zcVvNmELxCbnVfV5VwjUroaXmhvyxswrVmJmf-4d4vekW-7mzM-i8AyPlv8HUgHteR2W3LWyndEcaA4oxDY6o9v0SDRMF_0Lz3XSdlMk-Lu-sZk4_9nDDnZ4bWvtLt_xqGjnzMj3keEgnLj2JmEq1HAjssmSi4_kHEw3L-YrPpnj8G8wfxDTLsND6VJWDvqAoZSZJfHzwAd8RUdW5AHpxLVB5cf0zarxK7e0ziVnNt9EPov3o6ydxTNPPzvoAj-Lz9ZoLlwOwzbmatxpQzDJjZ2JiB_dy-clWVpjWeod6_ejeef3yvGuxQywi8c-djXAdCKWzKuSvswnZx8a6xYdpfnDZzBSLcQj7mjiSnww07qAk4kM0ejfK21jQ2vFK-zpi0Tt8Nnxq-kukY3jFEE_32YRnOGwpI_yA1MaGv4ZCYLPwFMIQSj1OeRYy0J2JdCqhL-FPdDq3x57YnFAwTfe1qlo7fPg4YbhA7yu3TtNN_hEPp3ulKWrxjhCERXAPj6a4-5IEko20CHVWM5MXj7VpYaVhxvC_vsicGNxQ_gm5c2hWOXF-Pqb7SCPo9sYYwvWYC2Zz5qJuvMGQxgA4xMMp_nUXw1GNdwV33jKcUgG2KKDj4EB-tPcGGNGfFr6MufanVT23PbQucQcmI_fD38U-WsThNGMqrTIJXYrKus6IazEzHwzeBzWI9R10FBiH_2ph5O1I9zR_CtYR1VH0R3B54dA0Nvi87vFPbS01jJKNvhCDZXKiYiG1QdULAjwFCOi3-lUEFAuzCT3fNJwaSLLvZmtgyXvzP8cckVkcuwx1sm9aO_Y88Gh7zCOl9oWUhHSVAyW6oCom9UnZxs37TQ9mubPczhCC_8IwGHNSl88yISFrKU_9Nv5AUl8VknhC-3oumvh2NjNmOQxiKHZGUAf3nwWg_OKgJfsLEoZDOk0BKbpzWcIYC3-Ov5u1lhyBdqNtr2dH_TH-yN-QW9V1ijw9Z9F09_nyqLRKryz_DFiIfJeJrTCm7OQyzJNBSHhKTmq5uZ6P04J0SzcUMR2YgPqh7iduQ_P68UkVG9IYc0H7926fsff19qqzAaNlvQ_QDTNdCOX47SzhdsVcTAuyT8Tjf7uez9hGvsD-VrBbHUu7qSxhakixf7zIkBeVX7zBtDxsnn04dfjmNcXrIHVDH412VQnsfSxs4brPHERFO8JH43P91E2XjzQHd9ay31mBOenvbaoKfL77QFySG9lzRHkyaBlzcLn47H3B7aa3BpqF-BUOpzsi6iI5y5XLzf1BRGDxcBKClEt1jbJdtF6WQz7irimhSDE7u0GQERfYeQv8c2Ce9LfzNSK8jepjNsldT77Wy2C6LWzvu8neAgC6gcqqPnrejF4K_Mj6nDdQnYQzY2DhKAh4_TmBlJFVELDEeDflVvc0OzFmtAHqKjuOkUg0kETt-mtuqXNI2gnGf4hA6Db01kdQjLuDcNMyFBFLeyZooK8D_L6_AhqYE5k0hbD1Kle49fD74_zP5WswTJSBfp2Np_QmrKf-T5lFxfxHSyX_ulxCnA03Sz-VM9peB35hK-doD7yxcAqd0hyedMd9s6Ad9Lc8e-O9BeumscfYinzWTme05G1svA6WBcL_T8xvPztXS1aL-MI_XHpWXIn16eGgJ8S09rzIFl9dGX7F-7NtXPl89XyjdgCtZLBB0sg7UsQktK-l7v6BlQKJ88zJ4HG5WcEbRztB-xR6lFVM_-GooGICPns4Dl-RUlByjTe35lf5fPizL71FJS-2TNIEuZ7MbLhnbKxygBqNjDeOC-nxOBbP0Mw1izBacNqZCruiKeAmD3w8M8kVmJxf8Aq6vapZ9fj9M6J0h2oveY3VTXQYReF_LGGlfUGShU68SICuObQUhp6KsQe313CUkkG27-giIQp8dDgIG1acWPkCs7IglTu4c7oqeMDn4n6JUEp3lsntc60oJXOHUwxL-E8E6f5w2kceCz5PstUwlh-HO6YkIChOsXU3Ax_YFFG4zzHyrpg7tDt-ILtP5W98i9rOPhYIJfwi62w6S5pKyHKMBG-5eByO3I43Sf6WONDZAL3j6SOlAnW6_oHcFtWWu0L3tuAe8rl9-2h5Q6ziO05QDfyeyGB8IOFhu8laB47xigSmenLfz1qGO0X5VzGWlgG-oG5oIUo-NXYLFRlcH71APTTpnjw7tXtmu4it5TFLGQb-WQlgPaqs7TtNVMvDsApDb3YzWosew_eOPVx0kNcBsedgIW3L_3G6hZ7bmNr4yXL_Lt6wOHx6arxAaKU3RhOA_FDG532jai71xFlLhnUADue8MlOK0I09ivladVjWB7XpIS2uSn1x_wlcm1Ocugdx_CXYuHT6_OE4giIuuE")
            .into(binding.imageview)
        binding.switch1.isChecked = read
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.switch1.setOnCheckedChangeListener { _, isReaded ->
            read = isReaded
        }

        onBackPressedDispatcher.addCallback(this){
            setResult(RESULT_OK, Intent().apply {
                putExtra(READ, read)
            })
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(READ, read)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        read = savedInstanceState.getBoolean(READ, false)
    }

    override fun onStart() {
        super.onStart()
        Log.e(LOG, "${this.componentName.className} onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(LOG, "${this.componentName.className} onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LOG, "${this.componentName.className} onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(LOG, "${this.componentName.className} onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.e(LOG, "${this.componentName.className} onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG, "${this.componentName.className} onDestroy")
    }

    companion object{
        const val READ = "READ"
    }
}