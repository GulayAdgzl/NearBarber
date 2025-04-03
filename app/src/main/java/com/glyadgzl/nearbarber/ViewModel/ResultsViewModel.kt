class ResultsViewModel {
    private val repository = ResultsRepository()
 
    fun loadSubCategory(id: String): LiveData<MutableList<CategoryModel>> {
        return repository.loadSubCategory(id)
    }

    fun loadPopular(id: String): LiveData<MutableList<StoreModel>> {
        return repository.loadPopular(id)
    }
 }