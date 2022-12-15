import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class Config {
  static const channel = 'lib.finpay.sdk/channels';
  static const platform = MethodChannel(channel);

  static void openApp() async {
    try {
      await platform.invokeMethod("openNativeApp");
    } on PlatformException catch (e) {
      debugPrint("Error: $e");
    }
  }

  static Future<String> getBalance() async {
    try {
      final result = await platform.invokeMethod("getBalance");
      return result;
    } on PlatformException catch (e) {
      debugPrint("Error: $e");
      return "0";
    }
  }
}
