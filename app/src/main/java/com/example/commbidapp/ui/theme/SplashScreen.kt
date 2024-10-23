import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.commbidapp.R
import com.example.commbidapp.ui.theme.CustomButton
import com.example.commbidapp.ui.theme.RegularFont
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.platform.LocalContext
import com.example.commbidapp.LoginActivity
import com.example.commbidapp.ui.theme.BlueCrayolaColor
import com.example.commbidapp.ui.theme.CelestialBlueColor

@Composable
fun SplashScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight
            val isLandscape = screenWidth > screenHeight
            val isTablet = (screenWidth > 600.dp && screenHeight > 900.dp) ||
                    (screenWidth > 900.dp && screenHeight > 600.dp)

            if (isTablet) {
                TabletLayout(isLandscape, onLoginClick, onRegisterClick)
            } else {
                PhoneLayout(isLandscape, onLoginClick, onRegisterClick)
            }
        }
    }
}

@Composable
fun TabletLayout(isLandscape: Boolean, onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_commbid),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(if (isLandscape) 300.dp else 350.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(if (isLandscape) 0.dp else 200.dp))


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomButton(
                text = "Login",
                onClick = {
                    onLoginClick()
                },
                backgroundColor = BlueCrayolaColor,
                fontFamily = RegularFont,
                modifier = Modifier.width(250.dp)
            )

            CustomButton(
                text = "Sign up",
                onClick = {
                    onRegisterClick()
                },
                backgroundColor = CelestialBlueColor,
                fontFamily = RegularFont,
                modifier = Modifier.width(250.dp)
            )
        }
    }
}

@Composable
fun PhoneLayout(isLandscape: Boolean, onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.Top
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_commbid),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(if (isLandscape) 200.dp else 300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(if (isLandscape) 0.dp else 200.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomButton(
                text = "Login",
                onClick = {
                    onLoginClick()
                },
                fontFamily = RegularFont,
                backgroundColor = BlueCrayolaColor

            )

            CustomButton(
                text = "Sign up",
                onClick = {
                    onRegisterClick()
                },
                backgroundColor = CelestialBlueColor,
                fontFamily = RegularFont
            )
        }
    }
}
