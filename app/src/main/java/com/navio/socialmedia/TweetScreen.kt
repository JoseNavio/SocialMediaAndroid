package com.navio.socialmedia

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun TweetScreenConstraint() {
    //Tweet
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val (
            avatar,
            tweet,
            divider
        ) = createRefs()
        val verticalGuide = createGuidelineFromStart(0.20f)
        val bottomBarrier = createBottomBarrier(avatar, tweet)

        //Avatar
        Column(modifier = Modifier
            .padding(8.dp)
            .constrainAs(avatar) {
                start.linkTo(parent.start)
                end.linkTo(verticalGuide)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            }) {
            Avatar()
        }
        //Content
        Column(modifier = Modifier
            .padding(8.dp)
            .constrainAs(tweet) {
                start.linkTo(avatar.end)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            }) {
            Content()
        }
        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(bottomBarrier)
                },
            color = Color.DarkGray,
            thickness = 1.dp
        )
    }

}

@Composable
fun Avatar() {
    Image(
        painter = painterResource(id = R.drawable.logo_aplitop_squared),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .aspectRatio(1f)
            .clip(shape = CircleShape)
    )
}

@Composable
fun Content() {
    Header()
    Spacer(modifier = Modifier.size(8.dp))
    Body()
    Spacer(modifier = Modifier.size(8.dp))
    Footer()
}

@Composable
fun Header() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        //Nick
        Text(
            text = "Aplitop",
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        //Email
        Text(
            text = "@Aplitop",
            textAlign = TextAlign.Start,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        //Last connection
        Text(
            text = "4h",
            textAlign = TextAlign.Start,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        //Menu icon
        Icon(
            imageVector = Icons.Default.MoreHoriz,
            contentDescription = "Menu",
            tint = Color.White
        )
    }
}

@Composable
fun Body() {
    Text(
        modifier = Modifier.padding(4.dp),
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam est justo, " +
                "vulputate sit amet consequat at, rhoncus et mauris. Suspendisse ultrices mi ut " +
                "lectus venenatis, ut faucibus justo dignissim. Cras in venenatis nisi, quis " +
                "sollicitudin libero.",
        textAlign = TextAlign.Start,
        color = Color.White
    )
    Spacer(modifier = Modifier.size(4.dp))
    Image(
        painter = painterResource(id = R.drawable.aplitop_advert),
        contentDescription = ("Image"),
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(3/2f),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Footer() {
    //Icon
    var chatIcon by remember { mutableIntStateOf(R.drawable.icon_chat) }
    //Counts
    var chatCount by remember { mutableIntStateOf(0) }
    var retweetCount by remember { mutableIntStateOf(0) }
    var likeCount by remember { mutableIntStateOf(0) }
    //Icons
    var chatColor by remember { mutableStateOf(Color.Gray) }
    var retweetColor by remember { mutableStateOf(Color.Gray) }
    var likeColor by remember { mutableStateOf(Color.Gray) }

    Row() {
        FooterIcon(
            id = chatIcon,
            description = "Icon chat",
            color = chatColor,
            modifier = Modifier.weight(1f),
            chatCount,
        ) {
            chatCount++
            chatColor = Color.White
            chatIcon = R.drawable.icon_chat_filled
        }
        FooterIcon(
            id = R.drawable.icon_rt,
            description = "Icon retweet",
            color = retweetColor,
            modifier = Modifier.weight(1f),
            retweetCount,
        ) {
            retweetCount++
            retweetColor = Color.White
        }
        FooterIcon(
            id = R.drawable.icon_like,
            description = "Icon like",
            color = likeColor,
            modifier = Modifier.weight(1f),
            likeCount,
        ) {
            likeCount++
            likeColor = Color.White
        }
    }
}

@Composable
fun FooterIcon(
    id: Int,
    description: String,
    color: Color,
    modifier: Modifier = Modifier,
    count: Int,
    clickAction: () -> Unit
) {

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .padding(end = 4.dp)
                .clickable {
                    clickAction()
                },
            painter = painterResource(id = id),
            contentDescription = description,
            tint = color
        )
        Text(
            text = count.toString(),
            fontSize = 12.sp,
            color = color,
            textAlign = TextAlign.Start,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable()
fun TweetPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF15202B)
    ) {
        TweetScreenConstraint()
    }
}