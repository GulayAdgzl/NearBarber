class ResultsRepository {

    private val firebaseDatabase=FirebaseDatabase.getInstance()
 
    fun loadSubCategory(id:String):LiveData<MutableList<CategoryModel>>{
 
        val listData=MutableLiveData<MutableList<CategoryModel>>()
        val ref=firebaseDatabase.getReference(path:"SubCategory")
        val query:Query=ref.orderByChild(path:"CategoryId").equalTo(id)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<CategoryModel>()
                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(CategoryModel::class.java)
                    if(list!=null){
                        list.add(list)
                    }
                }
                listData.value=list
            }
 
            override fun onCancelled(error: DatabaseError) {
                TODO(reason:"Not yet implemented")
            }
        })
        return listData
    }
 }