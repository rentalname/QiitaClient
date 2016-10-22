package sample.qiitaclient.view

import android.content.Context
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import sample.qiitaclient.R
import sample.qiitaclient.databinding.ViewArticleBinding
import sample.qiitaclient.model.Article

@BindingMethods(BindingMethod(type = Article::class,
        attribute = "bind:article",
        method = "setArticle"))
class ArticleView : FrameLayout {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val binding: ViewArticleBinding

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_article, this, true)
    }

    fun setArticle(article: Article) {
        binding.article = article
    }

}