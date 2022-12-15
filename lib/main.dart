import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:merchant_finpay_flutter/config.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  String txtBalance = "";

  void _incrementCounter() {
    // setState(() {
    //   // This call to setState tells the Flutter framework that something has
    //   // changed in this State, which causes it to rerun the build method below
    //   // so that the display can reflect the updated values. If we changed
    //   // _counter without calling setState(), then the build method would not be
    //   // called again, and so nothing would appear to happen.
    //   _counter++;
    // });
    _openApplication();
  }

  static const CHANNEL = 'lib.finpay.sdk/channels';
  static const platform = const MethodChannel(CHANNEL);

  _logout() async {
    try {
      await platform.invokeMethod("logout");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  _connectAccount() async {
    try {
      await platform.invokeMethod("connectAccount");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  _openApplication() async {
    try {
      await platform.invokeMethod("openApplication");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  _openQris() async {
    try {
      await platform.invokeMethod("openQris");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  _openTelkom() async {
    try {
      await platform.invokeMethod("openTelkom");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  _openWallet() async {
    try {
      await platform.invokeMethod("openWallet");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  _getUserBalance() async {
    try {
      await platform.invokeMethod("getUserBalance");
    } on PlatformException catch(e){
      print(e.message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            MaterialButton(
              onPressed: () {
                Config.openApp();
              },
              child: const Text("Opeen App"),
            ),
            MaterialButton(
              onPressed: () async {
                final result = await Config.getBalance();
                setState(() {
                  txtBalance = "Get Balance :$result";
                });
              },
              child: Text(txtBalance),
            ),
            SizedBox(height: 20,),
            // _roundedWidget(
            //     text: "Logout",
            //     backgroundColor: Color(0xFF333333),
            //     textColor: Color(0xFFFFFFFF),
            //     iconColor: Color(0xFFFFFFFF),
            //     onTap: () async {
            //       await _logout();
            //     }
            // ),
            // SizedBox(height: 20,),
            // _roundedWidget(
            //     text: "Open",
            //   backgroundColor: Color(0xFF333333),
            //   textColor: Color(0xFFFFFFFF),
            //   iconColor: Color(0xFFFFFFFF),
            //   onTap: () async {
            //       await _openApplication();
            //   }
            // )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async{
          await _openTelkom();
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  Widget _roundedWidget({
    required String? text,
    Color? backgroundColor,
    Color? iconColor,
    Color? textColor,
    Function? onTap
  }) {
    return GestureDetector(
      onTap: () async => onTap,
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20),
          color: backgroundColor
        ),
        padding: EdgeInsets.all(10),
        child: Column(
          children: [
            Icon(
              Icons.history,
              color: iconColor,
            ),
            SizedBox(height: 5,),
            Text(
              text!,
              style: TextStyle(
                color: textColor
              ),
            )
          ],
        ),
      ),
    );
  }
}
