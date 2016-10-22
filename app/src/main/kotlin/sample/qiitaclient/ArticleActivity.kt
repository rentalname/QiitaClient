package sample.qiitaclient

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sample.qiitaclient.databinding.ActivityArticleBinding
import sample.qiitaclient.model.Article

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
        val binding: ActivityArticleBinding = DataBindingUtil.setContentView(this, R.layout.activity_article)
        val article : Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        binding.article = article
    }
}
