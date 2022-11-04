package com.wahyurhy.bukugaji.view.result;

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

        initData();
    }

    private void initData() {
        Bundle data = getIntent().getExtras();
        if (!data.isEmpty()) {

            mEdtFullName.setText(data.getString("nama"));
            mEdtJabatan.setText(data.getString("jabatan"));

            String gajiPokok = data.getString("gaji_pokok");
            mEdtGajiPokok.setText(formatRupiah(Integer.parseInt(gajiPokok)));

            String lembur = data.getString("lembur");
            mEdtLembur.setText(formatRupiah(Integer.parseInt(lembur)));

            String tunjanganAnak = data.getString("tunjangan_anak");
            mEdtTunjanganAnak.setText(formatRupiah(Integer.parseInt(tunjanganAnak)));

            String potonganKoperasi = data.getString("potongan_koperasi");
            mEdtPotonganKoperasi.setText(formatRupiah(Double.parseDouble(potonganKoperasi)));

            String totalGaji = data.getString("total_gaji");
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