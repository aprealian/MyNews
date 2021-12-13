package me.aprilian.mynews.core.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

/**
 * Abstract Endless ScrollListener
 *
 */
abstract class EndlessScrollListener : RecyclerView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 10

    // The current offset index of data you have loaded
    private var currentPage = 1

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    // The total number of items in the data set after the last load
    private var previousTotal = 0
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var layoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: RecyclerView.LayoutManager) {
        validateLayoutManager(layoutManager)
        this.layoutManager = layoutManager
    }

    constructor(
        visibleThreshold: Int,
        layoutManager: RecyclerView.LayoutManager, startPage: Int
    ) {
        validateLayoutManager(layoutManager)
        this.visibleThreshold = visibleThreshold
        this.layoutManager = layoutManager
        currentPage = startPage
    }

    @Throws(IllegalArgumentException::class)
    private fun validateLayoutManager(layoutManager: RecyclerView.LayoutManager?) {
        require(
            !(null == layoutManager || layoutManager !is GridLayoutManager)
        ) { "LayoutManager must be of type GridLayoutManager or LinearLayoutManager." }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        if (layoutManager is GridLayoutManager) {
            firstVisibleItem = (layoutManager as GridLayoutManager)
                .findFirstVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            firstVisibleItem = (layoutManager as LinearLayoutManager)
                .findFirstVisibleItemPosition()
        }
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading
            && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold
        ) {
            // End has been reached do something
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int)
}