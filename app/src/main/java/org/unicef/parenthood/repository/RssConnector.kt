package org.unicef.parenthood.repository

import org.unicef.parenthood.repository.model.ArticleEntity

class RssConnector(val feedUrl: String): SourceReader {
    override fun readSource(): MutableList<ArticleEntity> {
        //todo
        return arrayListOf()
    }
}