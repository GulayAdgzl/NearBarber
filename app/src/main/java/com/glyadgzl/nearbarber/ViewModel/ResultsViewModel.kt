class ResultsViewModel {
    private val repository = ResultsRepository()
 
    fun loadSubCategory(id: String): LiveData<MutableList<CategoryModel>> {
        return repository.loadSubCategory(id)
    }
 }