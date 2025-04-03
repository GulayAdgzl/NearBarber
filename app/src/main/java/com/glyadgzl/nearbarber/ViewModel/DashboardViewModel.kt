import androidx.lifecycle.LiveData

class DashboardViewModel{
    private val repository=DashboardViewModel()
    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBannner()
    }
}