package com.techvipin130524.simplecalc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techvipin130524.simplecalc.ui.theme.SimpleCalcTheme

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var expression by remember { mutableStateOf("4,840 + 120 / 30") }
    var result by remember { mutableStateOf("= 4,844") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        TopBar()
        Spacer(modifier = Modifier.weight(1f))
        DisplaySection(expression, result)
        Spacer(modifier = Modifier.height(24.dp))
        KeypadSection()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.OpenInFull,
            contentDescription = "Expand",
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.size(20.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DragHandle,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.GridView,
                contentDescription = "History",
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun DisplaySection(expression: String, result: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = expression,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = result,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun KeypadSection() {
    val buttons = listOf(
        listOf("C", "⌫", "/", "*"),
        listOf("7", "8", "9", "-"),
        listOf("4", "5", "6", "+"),
        listOf("1", "2", "3", "="), // Simple grid for now, can refine tall buttons
        listOf("🔄", "0", ".", "")
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { label ->
                    if (label.isNotEmpty()) {
                        CalcButton(
                            label = label,
                            modifier = Modifier.weight(1f),
                            onClick = {}
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CalcButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val isOperator = label in listOf("/", "*", "-", "+", "=")
    val isAction = label in listOf("C", "⌫")
    
    val bgColor = when {
        isOperator -> MaterialTheme.colorScheme.primary
        isAction -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.tertiary
    }
    
    val contentColor = when {
        isOperator -> MaterialTheme.colorScheme.onPrimary
        isAction -> MaterialTheme.colorScheme.onSecondary
        else -> MaterialTheme.colorScheme.onTertiary
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (label == "⌫") {
            Icon(Icons.AutoMirrored.Filled.Backspace, contentDescription = null, tint = contentColor)
        } else if (label == "🔄") {
            Icon(Icons.Default.PublishedWithChanges, contentDescription = null, tint = contentColor)
        } else {
            Text(
                text = label,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor
            )
        }
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun CalculatorPreviewDark() {
    SimpleCalcTheme(darkTheme = true) {
        CalculatorScreen()
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun CalculatorPreviewLight() {
    SimpleCalcTheme(darkTheme = false) {
        CalculatorScreen()
    }
}
