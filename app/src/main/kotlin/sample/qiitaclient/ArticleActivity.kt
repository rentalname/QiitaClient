package sample.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import sample.qiitaclient.model.Article
import sample.qiitaclient.view.ArticleView
import java.util.logging.Logger

class ArticleActivity : AppCompatActivity() {

    companion object {
        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java)
                        .putExtra(ARTICLE_EXTRA, article)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleView = findViewById(R.id.article_view) as ArticleView
        val webView = findViewById(R.id.web_view) as WebView

        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        Logger.getLogger("†††").info(article.url)
        webView.loadUrl(article.url)
    }
}
