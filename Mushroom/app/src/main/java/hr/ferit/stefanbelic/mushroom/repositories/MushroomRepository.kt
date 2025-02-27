package hr.ferit.stefanbelic.mushroom.repositories

import com.google.firebase.firestore.FirebaseFirestore
import hr.ferit.stefanbelic.mushroom.common.Resource
import hr.ferit.stefanbelic.mushroom.models.MashroomInfo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

const val COLLECTION_JESTIVE_GLJIVE = "jestive_gljive"
const val COLLECTION_OTROVNE_GLJIVE = "otrovne_gljive"

class MushroomRepository @Inject constructor() {

    suspend fun getJestiveGljiveMushroomList(): Flow<Resource<List<MashroomInfo>>> = callbackFlow {
        val snapshotListener = FirebaseFirestore.getInstance().collection(COLLECTION_JESTIVE_GLJIVE)
            .addSnapshotListener { snapshot, e ->
                val snap = if (snapshot != null) {
                    Resource.Success(snapshot.toObjects(MashroomInfo::class.java))
                } else {
                    Resource.Error(e?.message ?: "Something went wrong")
                }
                trySend(snap)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    suspend fun removeJestiveMushroom(uId: String): Flow<Resource<Unit>> = flow {
        try {
            val querySnapshot =
                FirebaseFirestore.getInstance().collection(COLLECTION_JESTIVE_GLJIVE)
                    .whereEqualTo("uid", uId).get().await()

            for (document in querySnapshot.documents) {
                FirebaseFirestore.getInstance().collection(COLLECTION_JESTIVE_GLJIVE)
                    .document(document.id).delete().await()
            }

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Failed to remove data"))
        }
    }

    suspend fun addJestiveMushroom(mashroomInfo: MashroomInfo): Flow<Resource<String>> =
        callbackFlow {
            try {
                FirebaseFirestore.getInstance().collection(COLLECTION_JESTIVE_GLJIVE)
                    .add(mashroomInfo).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(Resource.Success("Gljiva je dodana"))
                        } else {
                            trySend(Resource.Error("Failed to add data: ${task.exception?.message}"))
                        }
                    }

                awaitClose {

                }
            } catch (e: Exception) {
                trySend(Resource.Error("Failed to add data: ${e.message}"))
            }
        }

    suspend fun getOtrovneGljiveMushroomList(): Flow<Resource<List<MashroomInfo>>> = callbackFlow {
        val snapshotListener = FirebaseFirestore.getInstance().collection(COLLECTION_OTROVNE_GLJIVE)
            .addSnapshotListener { snapshot, e ->
                val snap = if (snapshot != null) {
                    Resource.Success(snapshot.toObjects(MashroomInfo::class.java))
                } else {
                    Resource.Error(e?.message ?: "Something went wrong")
                }
                trySend(snap)
            }
        awaitClose {
            snapshotListener.remove()
        }

    }

    suspend fun removeOtrovneMushroom(uId: String): Flow<Resource<Unit>> = flow {
        try {
            val querySnapshot =
                FirebaseFirestore.getInstance().collection(COLLECTION_OTROVNE_GLJIVE)
                    .whereEqualTo("uid", uId).get().await()

            for (document in querySnapshot.documents) {
                FirebaseFirestore.getInstance().collection(COLLECTION_OTROVNE_GLJIVE)
                    .document(document.id).delete().await()
            }

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Failed to remove data"))
        }
    }

    suspend fun addOtrovneMushroom(mashroomInfo: MashroomInfo): Flow<Resource<String>> =
        callbackFlow {
            try {
                FirebaseFirestore.getInstance().collection(COLLECTION_OTROVNE_GLJIVE)
                    .add(mashroomInfo).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(Resource.Success("Gljiva je dodana"))
                        } else {
                            trySend(Resource.Error("Failed to add data: ${task.exception?.message}"))
                        }
                    }

                awaitClose {

                }
            } catch (e: Exception) {
                trySend(Resource.Error("Failed to add data: ${e.message}"))
            }
        }

}