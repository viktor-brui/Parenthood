package org.unicef.parenthood.repository

import org.unicef.parenthood.repository.model.ArticleEntity

interface SourceReader {
    fun readSource(): MutableList<ArticleEntity>
}