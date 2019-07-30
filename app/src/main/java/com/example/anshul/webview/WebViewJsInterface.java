package com.example.anshul.webview;

import android.content.Context;
import android.os.ParcelUuid;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import io.branch.referral.Branch;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerResponse;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.Branch.BranchReferralInitListener;
import io.branch.referral.Branch.BranchReferralStateChangedListener;
import io.branch.referral.BranchError;
import io.branch.referral.BranchViewHandler;
import io.branch.referral.Defines;
import io.branch.referral.SharingHelper;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchContentSchema;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ProductCategory;
import io.branch.referral.util.ShareSheetStyle;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anshul on 18/04/17.
 */


public class WebViewJsInterface {

  private WebView webView;
  private Context context;

  public WebViewJsInterface(Context context, WebView webView) {
    this.webView = webView;
    this.context=context;
  }

  @JavascriptInterface
  public void handleMessage(String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

  @JavascriptInterface
  public void trackEvent(String title, String jsonData) {
    Toast.makeText(context, "Title: " + title  + " Message: " + jsonData, Toast.LENGTH_LONG).show();
    try {
      JSONObject eventData = new JSONObject(jsonData); //Convert from string to object, can also use JSONArray
      new BranchEvent(BRANCH_STANDARD_EVENT.ADD_TO_CART)
              .setAffiliation("test_affiliation")
              .setCoupon("Coupon Code")
              .setDescription("Customer added item to cart")
              .setShipping(0.0)
              .setTax(9.75)
              .setRevenue((Double)eventData.getDouble("pack_amount"))
              .setSearchQuery("Test Search query")
              .addCustomDataProperty("Custom_Event_Property_Key1", "Custom_Event_Property_val1")
              .addCustomDataProperty("Custom_Event_Property_Key2", "Custom_Event_Property_val2")
              .logEvent(context);
    } catch (Exception ex) {}

  }

  @JavascriptInterface
  public void setWebViewTextCallback(){
    String script = WebViewUtils.formatScript("setText","This is a text from Android which is set " +
        "in the html page.");
    WebViewUtils.callJavaScriptFunction(webView,script);
  }
}
