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
            MethodChannel(it, CHANNEL).setMethodCallHandler { call, result ->
                openQris()
            }
            MethodChannel(it, CHANNEL).setMethodCallHandler { call, result ->
                openTelkom()
            }
        }
    }

    private fun openQris() {
        FinpaySDKUI.qrisUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential())
    }

    private fun openApplication() {
        FinpaySDKUI.applicationUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential())
    }

    private fun openTelkom() {
        FinpaySDKUI.telkomUIBuilder(java.util.UUID.randomUUID().toString(), this@MainActivity, credential())
    }

    private fun openopenQrisWallet() {
        FinpaySDKUI.walletUIBuilder("apptest", this@MainActivity, credential())
    }

    private fun connectAccount() {
        FinpaySDKUI.connectAccount("apptest", this@MainActivity, credential())
    }

    private fun logout() {
        FinpaySDKUI.logout(this@MainActivity, {
            println("success logout")
        })
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
