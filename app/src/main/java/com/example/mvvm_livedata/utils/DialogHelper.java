package com.example.mvvm_livedata.utils;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.example.mvvm_livedata.web.CallbackG;

/**
 * Created by Onner Tech on 03 May 2017.
 */

public class DialogHelper
{
	private static final DialogHelper ourInstance = new DialogHelper();
	private              AlertDialog  alertDialog;
	private              AlertDialog  dialog;

	private DialogHelper()
	{
	}

	public static DialogHelper getInstance()
	{
		return ourInstance;
	}

	public void showInformation(Context context, String message, final CallbackG<String> callBackG)
	{
		showInformation(context, "", message, callBackG);
	}

	public void showInformation(Context context, String title, String message, final CallbackG<String> callBackG)
	{

//		if (alertDialog != null) {
//			if (alertDialog.isShowing()) {
//				alertDialog.dismiss();
//			}
//		}
//
//		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
//		builder.setTitle(title);
//		builder.setMessage(message);
//		builder.setCancelable(false);
//		builder.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener()
//		{
//			@Override
//			public void onClick(DialogInterface dialogInterface, int i)
//			{
//				if (alertDialog != null) {
//					if (alertDialog.isShowing()) {
//						alertDialog.dismiss();
//					}
//				}
//
//				callBackG.CallBackG("");
//			}
//		});
//		alertDialog = builder.show();
	}

	public void showWithAction(Context context, String message, final CallbackG<Boolean> callBackG)
	{
//		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogCustom);
//		builder.setTitle("");
//		builder.setMessage(message);
//		builder.setCancelable(false);
//		builder.setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener()
//		{
//			@Override
//			public void onClick(DialogInterface dialogInterface, int i)
//			{
//				callBackG.CallBackG(true);
//			}
//		});
//		builder.setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener()
//		{
//			@Override
//			public void onClick(DialogInterface dialogInterface, int i)
//			{
//				callBackG.CallBackG(false);
//			}
//		});
//		try {
//			if (dialog != null) {
//				dialog.dismiss();
//			}
//			dialog = builder.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * check clipboard and return string if any, else return null.
	 *
	 * @return
	 */
	private final String getClipboardText(Context context)
	{

		// Gets a handle to the Clipboard Manager
		ClipboardManager clipboard = (ClipboardManager)
				context.getSystemService(Context.CLIPBOARD_SERVICE);

		// Gets the clipboard data from the clipboard
		ClipData clip = clipboard.getPrimaryClip();
		if (clip != null) {
			// Gets the first item from the clipboard data
			ClipData.Item item = clip.getItemAt(0);

			if (item != null) {
				return item.coerceToText(context).toString();
			}
		}
		return null;
	}

//	public void updateRequiredDialog(final Context context, String message, final CallBackG<Boolean> yesPressed)
//	{
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,
//				R.style.AlertDialogCustom));
//
//		alertDialogBuilder.setTitle(context.getString(R.string.app_name));
//		alertDialogBuilder.setMessage(message);
//		alertDialogBuilder.setCancelable(false);
//		alertDialogBuilder.setPositiveButton(context.getString(R.string.update), new DialogInterface.OnClickListener()
//		{
//			public void onClick(DialogInterface dialog, int id)
//			{
//				try {
//					dialog.cancel();
//					context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
//				} catch (Exception e) {
//					e.printStackTrace();
//					yesPressed.CallBackG(false);
//					return;
//				}
//				yesPressed.CallBackG(true);
//			}
//		});
//		alertDialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
//		{
//			public void onClick(DialogInterface dialog, int id)
//			{
//				dialog.cancel();
//				yesPressed.CallBackG(false);
//
//			}
//		});
//		alertDialogBuilder.show();
//	}
}
