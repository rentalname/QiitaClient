package sample.qiitaclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.qiitaclient.client.ArticleClient
import sample.qiitaclient.model.Article
import sample.qiitaclient.model.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://qiita.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

        val articleClient = retrofit.create(ArticleClient::class.java)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(
                dummyArticle("Kotlin入門", "ashina"),
                dummyArticle("Java入門", "ishida")
        )

        val listView: ListView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let { startActivity(it) }
        }
    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    title = title,
                    url = "https://http.cat/202",
                    user = User(id = "", name = userName, profileImageUrl = ""))
}
