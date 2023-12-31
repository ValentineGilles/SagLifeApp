import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.saglife.R
import com.example.saglife.database.getUsernameFromUid
import com.example.saglife.models.ForumCommentItem
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
private val auth = Firebase.auth

@Composable
fun ForumCommentCard (navController : NavHostController, postId : String, data : ForumCommentItem)
{
    // Gestion du nom d'auteur
    var author by remember { mutableStateOf("") }

    // Récupération du nom de l'auteur en fonction de l'ID de l'auteur
    if (data.author_id != "") {
        getUsernameFromUid(data.author_id) { username ->
            author = username
        }
    }

    // Affichage de la carte du commentaire
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp, bottom = 16.dp)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Affichage de l'icône du profil de l'auteur
                Icon(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Affichage du nom de l'auteur
                Text(text = author, style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(8.dp))
                // Affichage de la date et de l'heure du commentaire
                Text(
                    text = "${data.getDay()} à ${data.getTime()}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Affichage du contenu du commentaire
            Text(text = data.comment)
            println("id_comment = ${data.comment_id}")
            println("id_post = ${postId}")
            // Affichage du lien "Modifier" si l'utilisateur actuel est l'auteur du commentaire
            if (data.author_id == auth.currentUser?.uid) {
                Text(
                    text = "Modifier",
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("forum/modifycomment/${postId}/${data.comment_id}")
                        }
                        .fillMaxWidth()
                )
            }
        }
    }
}
