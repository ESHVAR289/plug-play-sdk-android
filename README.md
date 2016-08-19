
# Plug & Play SDK Integration

Citrus SDK UI is an android Library project for easy integration of citrus pay library to your project. It provides all the screens necessary for making a transaction via citrus pay.

## Services offered

*     Use the citrus payment gateway to do easy transactions with minimum effort. 
*     Citrus wallet access and get citrus cash balance. 
*     Save cards to user account. 
*     Delete saved cards. 
*     Pay Using Citrus Cash - user can make a payment using Citrus Cash account if he has sufficient amount for payment. 
*     Load Money – money can be loaded to user’s account using CC/DC/NB option. 
*     Withdraw money from wallet.


## Prerequisite
### You must already have installed and configured:

*     Java JDK version 1.6 or greater.
*     Android SDK Platform 22 (Android 5.1.1)
*     A Git client
*     Eclipse IDE with ADT or Android Studio
*     All Citrus PG Prerequisites.

*Note: Please DO NOT PROCEED if the above mentioned requirements have not been met. Citrus PG Prerequisites

### You need to enroll with Citrus as a merchant.

Make sure that you have the following parameters from Citrus.

* **Secret Key**
*     **Access Key**
*     **SignIn Key**
*     **SignIn Secret**
*     **SignUp Key**
*     **SignUp Secret**
*     **Bill Generator** Hosted on your server.
*     **Redirect URL** page hosted on your server. (After the transaction is complete, Citrus posts a response to this URL.) 
*     **Redirect URL-LoadCash** page hosted on your server (To handle LoadMoney response, refer this [link](https://github.com/citruspay/citrus-android-sdk/tree/master/backend-files))

**Fetch the following details for either [Production](http://www.citruspay.com/) or [Sandbox](http://sandbox.citruspay.com/) before proceeding**

A demonstration on navigating to the necessary screens to get the details are as follows: 

  ![Demo Image]( https://cldup.com/pG7aGwfHDh.gif)

## SDK initialization
Add below Compile dependencies to your project's level **build.gradle**
```javascript
  //Dependency For Citrus Plug & Play SDK
  compile('com.citruspay.sdk:plug-n-play-sdk:1.1') {
        transitive = true;
        exclude module: 'payment-sdk'
    }
  //Dependency For Citrus Core SDK  
  compile 'com.citruspay.sdk:payment-sdk:4.0.0' 
```

## SDK Configuration

Use **CitrusFlowManager.initCitrusConfig()** method before starting the payment flow.

**Method:**
```javascript
  initCitrusConfig(String signupId, String signupSecret,
   String signinId, String signinSecret,
   int actionBarItemColor, Context context,
   Environment environment,String vanity
   String billGenerator,String returnURL,boolean enableLogs)
```
you can get **Signup Id**, **Signup Secret**, **Signin Id**, **Signin** Secret as described earlier in the document. 
   
* **ActionabarItemColor** - is the color of your actionbar Items e.g. back button,Screen title, etc.
* **context** - you can Initialize this with getApplicationContext() or in case of fragment via **getActivity().getApplicationContext().**
* **Vanity** - you can get this by creating an account with citrus.
* **environment** - can be Environment.PRODUCTION (for production) Environment.SANDBOX(for sandbox). 
* **billGenerator** - Url of the Bill Generator Hosted on your server.
* **returnURL** - Redirect URL page hosted on your server.
* **enableLogs** - Flag to enable/disable core SDK logs.

**Sample:**
```javascript
  CitrusFlowManager.initCitrusConfig("test-signup",
   "c78ec84e389814a05d3ae46546d16d2e", "test-signin",
   "52f7e15efd4208cf5345dd554443fd99",
    getResources().getColor(R.color.white),YourActivity.this,
    Environment.SANDBOX,”prepaid”, sandboxBillGeneratorURL , sandboxReturnURL , false);
```

## Start Shopping Flow

**Use CitrusFlowManager.startShoppingFlow() method before starting the payment flow.**

**Method:**
```javascript
  startShoppingFlow(Context context, String email, String phone,           
                  String amount)
```

*  **context** - context of your activity.
*  **email** - users Email.
*  **phone** - users phoneNo.
*  **amount** - amount to be paid.
*  **overrideResultScreen** - flag to override the ResultScreen.  

**Sample:**

```javascript

  CitrusFlowManager.startShoppingFlow(YourActivity.this,       
    "developercitrus@mailinator.com", "8424019644", "5");
    
```

## Start Wallet Flow

**Use CitrusFlowManager.startShoppingFlow() method before starting the wallet flow.**

**Method:**
```javascript
  startWalletFlow(Context context, String email, String phone)
```
* **context** - context of your activity.
* **email** - users Email.
* **phone** - users phoneNo.

**Sample :**
```javascript

  CitrusFlowManager.startWalletFlow(YourActivity.this, 
        "developercitrus@mailinator.com", "8424019644");
        
```

If you want start your flow with custom theme then you need to add **@style/CitrusAppTheme** as a parent of your custom theme

**Like this:**
```xml

  <style name="AppTheme.pink" parent="CitrusAppTheme"> 
      <item name="colorPrimary">#EB1E63</item> 
      <item name="colorPrimaryDark">#C0175C></item> 
      <item name="colorAccent">#C0175C></item> 
      <item name="colorButtonNormal">#EB1E63></item>
      <item name="alertDialogTheme">@style/AlertDialogStyle_pink></item>
      ...
  </style>
  <style name="AlertDialogStyle_pink"parent="Theme.AppCompat.Light.Dialog.Alert"> 
      <item name="colorAccent">#C0175C/item> 
      <item name=“android:textColorPrimary">@color/citrus_text_color</item>
      ...
  </style>

```

**Usage of Custom theme**
```javascript

    CitrusFlowManager.startShoppingFlowStyle(MainActivity.this,
                        dummyEmail, dummyMobile, dummyAmount, R.style.AppTheme_pink, false);
                        
```                        
