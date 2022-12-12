package merchant.finpay.flutter.merchant_finpay_flutter

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.plugins.util.GeneratedPluginRegister
import io.flutter.plugin.common.MethodChannel
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI

class MainActivity: FlutterActivity() {
    private val CHANNEL = "lib.finpay.sdk/channels"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegister.registerGeneratedPlugins(FlutterEngine(this@MainActivity))
        flutterEngine?.dartExecutor?.let {
            MethodChannel(it, CHANNEL).setMethodCallHandler { call, result ->
                openApplication()
            }
        }
    }

    private fun openApplication() {
        FinpaySDKUI.openApplication(this@MainActivity, credential())
    }

    private fun getUserBalance(
        onResult: (String) -> Unit
    ) {
        FinpaySDK.getUserBallance(
            java.util.UUID.randomUUID().toString(), //random string
            this, {
                onResult(it.amount!!)
            },{
                onResult("0")
            })
    }

    fun credential(): Credential {
        val cd = Credential()
        cd.setUsername("MT77764DKM83N")
        cd.setPassword("YJV3AM0y")
        cd.setSecretKey("daYumnMb")
        cd.setPhoneNumber("083815613839")
        cd.setCustName("Widiyanto Ramadhan")
        return cd
    }
}
