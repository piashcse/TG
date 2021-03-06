package mp.piash.tg.fragment;


import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mp.piash.tg.R;
import mp.piash.tg.database.TechGaintHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankAccountFragment extends Fragment {

    public static final String TAG ="Account";
    private TextView mTextViewCash;
    private TextView mTextViewPersonalBankAccount;
    private TextView mTextViewCompanyBankAccount;
    private Button mButtonBack;
    private Button mButtonCashWithdraw;
    private Button mButtonCashDeposit;
    private Button mButtonCompanyBankAccountWithdraw;
    private Button mButtonCompanyBankAccountDeposit;
    private TechGaintHandler mTechGaintHandler;
    private EditText mEditTextTransferCashAccount;
    private EditText mEditTextTransferCompanyAccount;
    private Dialog mDialogForBank;
    private Button mButtonPersonalTransfer;
    private Button mButtonCashTransfer;

    public BankAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bank_account, container, false);
        mTextViewCash = (TextView)view.findViewById(R.id.tvCash);
        mTextViewPersonalBankAccount = (TextView)view.findViewById(R.id.tvPersonalBankAccount);
        mTextViewCompanyBankAccount = (TextView)view.findViewById(R.id.tvCompanyBankAccount);
        mButtonCompanyBankAccountWithdraw = (Button)view.findViewById(R.id.tvWithdrawCompanyBankAccount);
        mButtonCompanyBankAccountDeposit = (Button)view.findViewById(R.id.tvDepositCompanyBankAccount);
        mButtonBack = (Button) view.findViewById(R.id.BackButton);
        mButtonCashWithdraw = (Button) view.findViewById(R.id.tvWithdrawAccount);
        mButtonCashDeposit = (Button) view.findViewById(R.id.tvDepositAmount);
        mEditTextTransferCashAccount = (EditText)view.findViewById(R.id.etTransferCashAmount);
        mEditTextTransferCompanyAccount = (EditText)view.findViewById(R.id.etTransferCompanyAccount);
        mDialogForBank = new Dialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        mDialogForBank.setContentView(R.layout.layout_company_experience);
        mButtonPersonalTransfer = (Button) mDialogForBank.findViewById(R.id.tvDepositPersonalTransfer);
        mButtonCashTransfer = (Button) mDialogForBank.findViewById(R.id.tvDepositCashTransfer);

        mTechGaintHandler = new TechGaintHandler(getActivity());
        mTextViewCash.setText("Cash  $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
        mTextViewCompanyBankAccount.setText("Company BankAccount  $" +String.valueOf(mTechGaintHandler.getAllCompanyBankAccount().get(0)));
        mTextViewPersonalBankAccount.setText("Personal BankAccount  $" +String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));

        backButton();
        clickEvent();
        dialogPopUp();
        return view;
    }

    private void dialogPopUp(){

        mButtonPersonalTransfer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialogForBank.dismiss();
                        if (!mEditTextTransferCompanyAccount.getText().toString().isEmpty() && Integer.parseInt(mEditTextTransferCompanyAccount.getText().toString()) >= 100 && mTechGaintHandler.getAllCompanyBankAccount().get(0) >= Integer.parseInt(mEditTextTransferCompanyAccount.getText().toString()) ){

                            showDialogCompanyDeposit( "withdraw",Integer.valueOf(mEditTextTransferCompanyAccount.getText().toString()));
                        }else {
                            if (!mEditTextTransferCashAccount.getText().toString().isEmpty()){
                                if (Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) < 100){
                                    Toast(" Minimum Amount to open an Account is $100");
                                }else {
                                    Toast("Insufficient Balance");

                                }
                            }else {
                                Toast("Insufficient Balance");

                            }
                        }
                    }
                }
        );

        mButtonCashTransfer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialogForBank.dismiss();
                        if (!mEditTextTransferCompanyAccount.getText().toString().isEmpty() && Integer.parseInt(mEditTextTransferCompanyAccount.getText().toString()) >= 100 && mTechGaintHandler.getAllCompanyBankAccount().get(0) >= Integer.parseInt(mEditTextTransferCompanyAccount.getText().toString()) ){

                            showCompanyWithDraw( "withdraw",Integer.valueOf(mEditTextTransferCompanyAccount.getText().toString()));
                        }else {
                            if (!mEditTextTransferCashAccount.getText().toString().isEmpty()){
                                if (Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) < 100){
                                    Toast(" Minimum Amount to open an Account is $100");

                                }else {
                                    Toast("Insufficient Balance");

                                }
                            }else {
                                Toast("Insufficient Balance");

                            }

                        }
                    }
                }
        );
    }
    private void backButton(){
        mButtonBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFragmentManager().popBackStack();
                    }
                }
        );
    }
    public void clickEvent(){

        mButtonCashWithdraw.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mEditTextTransferCashAccount.getText().toString().isEmpty() && Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) >= 100 && mTechGaintHandler.getAllCash().get(0) >= Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) ){

                            showDialogWithDraw( "withdraw",Integer.valueOf(mEditTextTransferCashAccount.getText().toString()));
                        }else {
                            if (!mEditTextTransferCashAccount.getText().toString().isEmpty()){
                                 if (Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) < 100){
                                Toast(" Minimum Amount to open an Account is $100");
                                 }else {
                                     Toast("Insufficient Balance");

                                 }
                            }else {
                                Toast("Insufficient Balance");

                            }
                        }
                    }
                }
        );

        mButtonCashDeposit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mEditTextTransferCashAccount.getText().toString().isEmpty() && Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) >= 100 && mTechGaintHandler.getAllCash().get(0) >= Integer.parseInt(mEditTextTransferCashAccount.getText().toString())){
                            showDialogDeposit("Deposit" ,Integer.valueOf(mEditTextTransferCashAccount.getText().toString()));
                        }else {
                            if (!mEditTextTransferCashAccount.getText().toString().isEmpty()){
                                if (Integer.parseInt(mEditTextTransferCashAccount.getText().toString()) < 100) {
                                    Toast(" Minimum Amount to open an Account is $100");
                                }else {
                                    Toast("Insufficient Balance");
                                }
                            }else {
                                Toast("Insufficient Balance");

                            }
                        }


                    }
                }
        );

        mButtonCompanyBankAccountWithdraw.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialogForBank.show();
                    }
                }
        );
        mButtonCompanyBankAccountDeposit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialogForBank.show();

                    }
                }
        );
    }
    public void showDialogDeposit(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                int totalSumision =  mTechGaintHandler.getAllPersonalBankAccount().get(0) + balance ;
                int totalSubstract =  mTechGaintHandler.getAllCash().get(0) - balance ;
                mTechGaintHandler.updatePersonalBankAccount(totalSumision);
                mTechGaintHandler.updateCash(totalSubstract);
                Toast("your current Balance is "+mTechGaintHandler.getAllCash().get(0));
                mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                mTextViewPersonalBankAccount.setText("Personal BankAccount - $" +String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void showDialogCompanyDeposit(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                int totalSumision =  mTechGaintHandler.getAllCash().get(0) + balance ;
                int totalSubstract =  mTechGaintHandler.getAllCompanyBankAccount().get(0) - balance ;
                mTechGaintHandler.updateCash(totalSumision);
                mTechGaintHandler.updateCompanyBankAccount(totalSubstract);
                Toast("your current Balance is "+mTechGaintHandler.getAllCompanyBankAccount().get(0));
                mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                mTextViewCompanyBankAccount.setText("Company BankAccount - $" +String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showDialogWithDraw(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                int totalSumision =  mTechGaintHandler.getAllPersonalBankAccount().get(0) + balance ;
                int totalSubstract =  mTechGaintHandler.getAllCash().get(0) - balance ;
                mTechGaintHandler.updatePersonalBankAccount(totalSumision);
                mTechGaintHandler.updateCash(totalSubstract);
                Toast("your current Balance is "+mTechGaintHandler.getAllCash().get(0));
                mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                mTextViewPersonalBankAccount.setText("Personal BankAccount - $" +String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showCompanyWithDraw(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
              /*  int total = mTechGaintHandler.getAllCash().get(0) - balance;
                mTechGaintHandler.updateCash(total);
                Toast("your current Balance is "+mTechGaintHandler.getAllCash().get(0));
                mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
*/
                int totalSumision =  mTechGaintHandler.getAllPersonalBankAccount().get(0) + balance ;
                int totalSubstract =  mTechGaintHandler.getAllCompanyBankAccount().get(0) - balance ;
                mTechGaintHandler.updatePersonalBankAccount(totalSumision);
                mTechGaintHandler.updateCompanyBankAccount(totalSubstract);
                Toast("your current Balance is "+mTechGaintHandler.getAllCompanyBankAccount().get(0));
                mTextViewCompanyBankAccount.setText("Company BankAccount  $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                mTextViewPersonalBankAccount.setText("Personal BankAccount  $" +String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void Toast(String string){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_root,
                (ViewGroup)getView().findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(string);

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
