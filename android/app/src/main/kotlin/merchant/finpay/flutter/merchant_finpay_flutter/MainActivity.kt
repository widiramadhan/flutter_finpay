package merchant.finpay.flutter.merchant_finpay_flutter

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import lib.finpay.sdk.corekit.FinpaySDK
import lib.finpay.sdk.corekit.model.Credential
import lib.finpay.sdk.uikit.FinpaySDKUI

class MainActivity : FlutterActivity() {
    companion object {
        private const val CHANNEL = "lib.finpay.sdk/channels"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger, CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                "openNativeApp" -> {
                    openApplication()
                }
                "getBalance" -> {
                    getUserBalance(onResult = { result.success(it) })
                }
            }
        }
    }

    private fun openApplication() {
        FinpaySDKUI.applicationUIBuilder("", this@MainActivity, credential())
    }

    private fun getUserBalance(
        onResult: (String) -> Unit
    ) {
        FinpaySDK.getUserBallance(java.util.UUID.randomUUID().toString(), //random string
            this, {
                onResult.invoke(it.amount!!)
            }, {
                onResult.invoke("0")
            })
    }

    private fun credential(): Credential {
        val cd = Credential()
        cd.setUsername("MT77764DKM83N")
        cd.setPassword("YJV3AM0y")
        cd.setSecretKey("daYumnMb")
        cd.setPhoneNumber("083815613839")
        cd.setCustName("Widiyanto Ramadhan")
        return cd
    }
}
