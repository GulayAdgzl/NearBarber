class DashboardViewModel{
    privateval repository=DashboardViewModel()
    fun loadCategory():LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }
}