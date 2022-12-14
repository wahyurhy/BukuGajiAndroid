package com.wahyurhy.bukugaji.view.main;

import static com.wahyurhy.bukugaji.utils.Const.GAJI_BERSIH;
import static com.wahyurhy.bukugaji.utils.Const.GAJI_POKOK;
import static com.wahyurhy.bukugaji.utils.Const.JABATAN;
import static com.wahyurhy.bukugaji.utils.Const.LEMBUR;
import static com.wahyurhy.bukugaji.utils.Const.NAMA;
import static com.wahyurhy.bukugaji.utils.Const.POTONGAN_KOPERASI;
import static com.wahyurhy.bukugaji.utils.Const.TOTAL_GAJI;
import static com.wahyurhy.bukugaji.utils.Const.TUNJANGAN_ANAK;
import static com.wahyurhy.bukugaji.utils.Utils.setSystemBarColor;
import static com.wahyurhy.bukugaji.utils.Utils.setSystemBarLight;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.wahyurhy.bukugaji.R;
import com.wahyurhy.bukugaji.view.result.ResultActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private LinearLayout mLytHeader;
    private LinearLayout mLytFullName;
    private TextInputEditText mEdtFullName;
    private LinearLayout mLytJabatan;
    private TextInputEditText mEdtJabatan;
    private LinearLayout mLytTotalLembur;
    private TextInputEditText mEdtTotalLembur;
    private LinearLayout mLytJumlahAnak;
    private TextInputEditText mEdtJumlahAnak;
    private AppCompatButton mBtnHitung;

    private Dialog dialogJabatan;

    private String jabatan;
    private int gajiPokok, totalTunjanganAnak, tunjanganAnakPerAnak, totalLembur, upahLemburPerJam, gajiBersihInt, totalGajiInt;
    private double potonganKoperasi, pajak, gajiBersih, totalGaji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initView();
        initClick();
    }

    private void initClick() {
        mEdtJabatan.setOnClickListener(this);
        mBtnHitung.setOnClickListener(this);
    }

    private void initialize() {
        setSystemBarLight(this);
        setSystemBarColor(this, R.color.white);
    }

    private void initView() {
        mLytHeader = findViewById(R.id.lyt_header);
        mLytFullName = findViewById(R.id.lyt_full_name);
        mEdtFullName = findViewById(R.id.edt_full_name);
        mLytJabatan = findViewById(R.id.lyt_jabatan);
        mEdtJabatan = findViewById(R.id.edt_jabatan);
        mLytTotalLembur = findViewById(R.id.lyt_total_lembur);
        mEdtTotalLembur = findViewById(R.id.edt_total_lembur);
        mLytJumlahAnak = findViewById(R.id.lyt_jumlah_anak);
        mEdtJumlahAnak = findViewById(R.id.edt_jumlah_anak);
        mBtnHitung = findViewById(R.id.btn_hitung);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edt_jabatan:
                showJabatanDialog();
                break;
            case R.id.btn_close_dialog:
                dialogJabatan.dismiss();
                break;
            case R.id.btn_pilih:
                dialogJabatan.dismiss();
                mEdtJabatan.setText(jabatan);
                break;
            case R.id.btn_hitung:
                checkEmptyEditText();
                break;
        }
    }

    private void resultActivity() {
        Intent intentResultActivity = new Intent(this, ResultActivity.class);

        intentResultActivity.putExtra(NAMA, String.valueOf(mEdtFullName.getText()));
        intentResultActivity.putExtra(JABATAN, String.valueOf(mEdtJabatan.getText()));
        intentResultActivity.putExtra(GAJI_POKOK, String.valueOf(gajiPokok));
        intentResultActivity.putExtra(GAJI_BERSIH, String.valueOf(gajiBersihInt));
        intentResultActivity.putExtra(LEMBUR, String.valueOf(totalLembur));
        intentResultActivity.putExtra(TUNJANGAN_ANAK, String.valueOf(totalTunjanganAnak));
        intentResultActivity.putExtra(POTONGAN_KOPERASI, String.valueOf(potonganKoperasi));
        intentResultActivity.putExtra(TOTAL_GAJI, String.valueOf(totalGajiInt));

        startActivity(intentResultActivity);
    }

    private void checkEmptyEditText() {
        Boolean isFullNameEmpty, isJabatanEmpty, isTotalLemburEmpty, isJumlahAnakEmpty;

        if (mEdtJabatan.getText().toString().trim().equalsIgnoreCase("")) {
            mEdtJabatan.setText("Harap di isi!");
            mEdtJabatan.setTextColor(getResources().getColor(R.color.red));
            isJabatanEmpty = true;
        } else {
            mEdtJabatan.setError(null);
            mEdtJabatan.setTextColor(getResources().getColor(R.color.defaultTextColor));
            isJabatanEmpty = false;
        }

        if (mEdtJumlahAnak.getText().toString().trim().equalsIgnoreCase("")) {
            mEdtJumlahAnak.setError("Harap di isi!");
            mEdtJumlahAnak.requestFocus();
            isJumlahAnakEmpty = true;
        } else {
            mEdtJumlahAnak.setError(null);
            isJumlahAnakEmpty = false;
        }

        if (mEdtTotalLembur.getText().toString().trim().equalsIgnoreCase("")) {
            mEdtTotalLembur.setError("Harap di isi!");
            mEdtTotalLembur.requestFocus();
            isTotalLemburEmpty = true;
        } else {
            mEdtTotalLembur.setError(null);
            isTotalLemburEmpty = false;
        }

        if (mEdtFullName.getText().toString().trim().equalsIgnoreCase("")) {
            mEdtFullName.setError("Harap di isi!");
            mEdtFullName.requestFocus();
            isFullNameEmpty = true;
        } else {
            mEdtFullName.setError(null);
            isFullNameEmpty = false;
        }

        Log.d("TAG", "checkEmptyEditText: " + isFullNameEmpty + isJabatanEmpty + isTotalLemburEmpty + isJumlahAnakEmpty);

        if (!isFullNameEmpty && !isJabatanEmpty && !isTotalLemburEmpty && !isJumlahAnakEmpty) {
            if (mEdtJabatan.getText().toString().equals("Harap di isi!")) {
                mEdtJabatan.setTextColor(getResources().getColor(R.color.red));
            } else {
                hitungGaji();
                resultActivity();
            }
        }


    }

    private void hitungGaji() {
        Log.d("TAG", "hitungGaji: hitungGaji()");
        upahLemburPerJam = 7000;
        tunjanganAnakPerAnak = 150000;
        potonganKoperasi = 0.05 * gajiPokok;
        totalLembur = Integer.parseInt(String.valueOf(mEdtTotalLembur.getText())) * upahLemburPerJam;
        totalTunjanganAnak = Integer.parseInt(String.valueOf(mEdtJumlahAnak.getText())) * tunjanganAnakPerAnak;
        pajak = (0.1 * gajiPokok) + totalLembur + totalTunjanganAnak - potonganKoperasi;
        gajiBersih = gajiPokok - pajak - potonganKoperasi + totalLembur + totalTunjanganAnak;
        totalGaji = gajiPokok + totalLembur + totalTunjanganAnak;
        Log.d("TAG", "hitungGaji: Gaji Bersih Sebelumnya = " + gajiBersih);

        gajiBersihInt = (int) gajiBersih;
        totalGajiInt = (int) totalGaji;
        Log.d("TAG", "hitungGaji: Total Tunjangan Anak = " + totalTunjanganAnak);
        Log.d("TAG", "hitungGaji: Potongan Koperasi = " + potonganKoperasi);
        Log.d("TAG", "hitungGaji: Upah Lembur = " + totalLembur);
        Log.d("TAG", "hitungGaji: Pajak = " + pajak);
        Log.d("TAG", "hitungGaji: Gaji Bersih = " + gajiBersihInt);
        Log.d("TAG", "hitungGaji: Total Gaji = " + totalGajiInt);
    }

    private void showJabatanDialog() {
        dialogJabatan = new Dialog(this);
        dialogJabatan.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialogJabatan.setContentView(R.layout.dialog_jabatan);
        dialogJabatan.setCancelable(false);
        dialogJabatan.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogJabatan.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ImageView closeButton = dialogJabatan.findViewById(R.id.btn_close_dialog);

        AppCompatButton btnSelect = dialogJabatan.findViewById(R.id.btn_pilih);

        RadioGroup jabatanGroup;
        jabatanGroup = dialogJabatan.findViewById(R.id.group_jabatan);

        jabatanGroup.setOnCheckedChangeListener(this);

        closeButton.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

        dialogJabatan.show();
        dialogJabatan.getWindow().setAttributes(lp);
        mEdtJabatan.setTextColor(getResources().getColor(R.color.defaultTextColor));
        mEdtJabatan.setText(jabatan);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_direktur:
                jabatan = "Direktur";
                gajiPokok = 20000000;
                break;
            case R.id.rb_hrd:
                jabatan = "HRD";
                gajiPokok = 15000000;
                break;
            case R.id.rb_sekertaris:
                jabatan = "Sekertaris";
                gajiPokok = 10000000;
                break;
            case R.id.rb_Karyawan:
                jabatan = "Karyawan";
                gajiPokok = 5000000;
                break;
            default:
                jabatan = "Harap di isi!";
                break;
        }
    }
}