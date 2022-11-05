package com.wahyurhy.bukugaji.view.result;

import static com.wahyurhy.bukugaji.utils.Const.GAJI_BERSIH;
import static com.wahyurhy.bukugaji.utils.Const.GAJI_POKOK;
import static com.wahyurhy.bukugaji.utils.Const.JABATAN;
import static com.wahyurhy.bukugaji.utils.Const.LEMBUR;
import static com.wahyurhy.bukugaji.utils.Const.NAMA;
import static com.wahyurhy.bukugaji.utils.Const.POTONGAN_KOPERASI;
import static com.wahyurhy.bukugaji.utils.Const.TOTAL_GAJI;
import static com.wahyurhy.bukugaji.utils.Const.TUNJANGAN_ANAK;
import static com.wahyurhy.bukugaji.utils.Utils.formatRupiah;
import static com.wahyurhy.bukugaji.utils.Utils.setSystemBarColor;
import static com.wahyurhy.bukugaji.utils.Utils.setSystemBarLight;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.wahyurhy.bukugaji.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvTotalGaji;
    private LinearLayout mLytFullName;
    private TextInputEditText mEdtFullName;
    private LinearLayout mLytJabatan;
    private TextInputEditText mEdtJabatan;
    private LinearLayout mLytGajiPokok;
    private TextInputEditText mEdtGajiPokok;
    private LinearLayout mLytLembur;
    private TextInputEditText mEdtLembur;
    private LinearLayout mLytTunjanganAnak;
    private TextInputEditText mEdtTunjanganAnak;
    private LinearLayout mLytPotonganKoperasi;
    private TextInputEditText mEdtPotonganKoperasi;
    private LinearLayout mLytGajiBersih;
    private TextInputEditText mEdtGajiBersih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialize();
        initView();
        initClick();
    }

    private void initClick() {
        mIvBack.setOnClickListener(this);
    }

    private void initialize() {
        setSystemBarLight(this);
        setSystemBarColor(this, R.color.white);
    }

    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
        mTvTotalGaji = findViewById(R.id.tv_total_gaji);
        mLytFullName = findViewById(R.id.lyt_full_name);
        mEdtFullName = findViewById(R.id.edt_full_name);
        mLytJabatan = findViewById(R.id.lyt_jabatan);
        mEdtJabatan = findViewById(R.id.edt_jabatan);
        mLytGajiPokok = findViewById(R.id.lyt_gaji_pokok);
        mEdtGajiPokok = findViewById(R.id.edt_gaji_pokok);
        mLytLembur = findViewById(R.id.lyt_lembur);
        mEdtLembur = findViewById(R.id.edt_lembur);
        mLytTunjanganAnak = findViewById(R.id.lyt_tunjangan_anak);
        mEdtTunjanganAnak = findViewById(R.id.edt_tunjangan_anak);
        mLytPotonganKoperasi = findViewById(R.id.lyt_potongan_koperasi);
        mEdtPotonganKoperasi = findViewById(R.id.edt_potongan_koperasi);
        mLytGajiBersih = findViewById(R.id.lyt_gaji_bersih);
        mEdtGajiBersih = findViewById(R.id.edt_gaji_bersih);

        initData();
    }

    private void initData() {
        Bundle data = getIntent().getExtras();
        if (!data.isEmpty()) {

            mEdtFullName.setText(data.getString(NAMA));
            mEdtJabatan.setText(data.getString(JABATAN));

            String gajiPokok = data.getString(GAJI_POKOK);
            mEdtGajiPokok.setText(formatRupiah(Integer.parseInt(gajiPokok)));

            String gajiBersih = data.getString(GAJI_BERSIH);
            mEdtGajiBersih.setText(formatRupiah(Integer.parseInt(gajiBersih)));

            String lembur = data.getString(LEMBUR);
            mEdtLembur.setText(formatRupiah(Integer.parseInt(lembur)));

            String tunjanganAnak = data.getString(TUNJANGAN_ANAK);
            mEdtTunjanganAnak.setText(formatRupiah(Integer.parseInt(tunjanganAnak)));

            String potonganKoperasi = data.getString(POTONGAN_KOPERASI);
            mEdtPotonganKoperasi.setText(formatRupiah(Double.parseDouble(potonganKoperasi)));

            String totalGaji = data.getString(TOTAL_GAJI);
            mTvTotalGaji.setText(formatRupiah(Integer.parseInt(totalGaji)));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}