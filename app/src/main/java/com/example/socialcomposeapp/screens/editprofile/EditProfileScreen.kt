import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.editprofile.EditProfileViewModel
import com.example.socialcomposeapp.screens.editprofile.components.GreetingMessage
import com.example.socialcomposeapp.screens.editprofile.components.ProfileDetailsCard
import com.example.socialcomposeapp.screens.editprofile.components.ProfilePicWithEditIcon
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfileScreen(
    navigate: (DestinationScreen) -> Unit,
    navigateBack: () -> Unit,
    viewModel: EditProfileViewModel = koinViewModel()
) {
    val name = viewModel.name.collectAsStateWithLifecycle().value
    val email = viewModel.email.collectAsStateWithLifecycle().value
    val bio = viewModel.bio.collectAsStateWithLifecycle().value
    val isLoading = viewModel.isLoading.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4E004A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            GreetingMessage(
                navigateBack
            )
            Spacer(modifier = Modifier.height(32.dp))
            ProfilePicWithEditIcon()
            Spacer(modifier = Modifier.height(32.dp))
            ProfileDetailsCard(
                name = name,
                email = email,
                bio = bio,
                onNameChange = { newName -> viewModel.onNameChange(newName) },
                onEmailChange = { newEmail -> viewModel.onEmailChange(newEmail) },
                onBioChange = { newBio -> viewModel.onBioChange(newBio) },
                onSaveClick = {
                    viewModel.saveUserData()
                    navigateBack()
                }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
