package com.carlostorres.iberica.model.intrfacs

import com.carlostorres.iberica.data.local.entity.Notes

interface HomeListener {
    fun onShowProgress()

    fun onHideProgress()

    fun addNote()

    fun noteClicked( autos: Notes, position: Int )
}