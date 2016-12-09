package antonioneto.unibratec.com.br.celebinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import antonioneto.unibratec.com.br.celebinfo.model.Celeb;

public class DetailCelebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_celeb);

        Celeb celeb = (Celeb) getIntent().getSerializableExtra("celeb");

        DetailCelebFragment dcf = DetailCelebFragment.newInstance(celeb);

        //Carregando o Fragment em tempo de execução
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_celeb_detail, dcf, "celeb")
                .commit();


    }
}
