package com.RahadiyanAristyo0052.Assesment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.RahadiyanAristyo0052.Assesment1.R
import com.RahadiyanAristyo0052.Assesment1.ui.theme.KalkulatorNilaiProdukTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                title = {
                    Text(text = stringResource(R.string.title_app))
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var product1Name by rememberSaveable { mutableStateOf("") }
    var product1Price by rememberSaveable { mutableStateOf("") }
    var product1Amount by rememberSaveable { mutableStateOf("") }
    var product1Unit by rememberSaveable { mutableStateOf("ml") }

    var product2Name by rememberSaveable { mutableStateOf("") }
    var product2Price by rememberSaveable { mutableStateOf("") }
    var product2Amount by rememberSaveable { mutableStateOf("") }
    var product2Unit by rememberSaveable { mutableStateOf("ml") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp)),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Image(
                painter = painterResource(R.drawable.product_compare_header),
                contentDescription = stringResource(R.string.image_desc),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(22.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 36.dp, height = 8.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )
            }

            Text(
                text = stringResource(R.string.intro_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(R.string.intro_desc),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.form_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.form_desc),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))

        ProductCard(
            title = stringResource(R.string.product_1),
            name = product1Name,
            onNameChange = { product1Name = it },
            price = product1Price,
            onPriceChange = { product1Price = it },
            amount = product1Amount,
            onAmountChange = { product1Amount = it },
            unit = product1Unit,
            onUnitChange = { product1Unit = it }
        )

        Spacer(modifier = Modifier.height(14.dp))

        ProductCard(
            title = stringResource(R.string.product_2),
            name = product2Name,
            onNameChange = { product2Name = it },
            price = product2Price,
            onPriceChange = { product2Price = it },
            amount = product2Amount,
            onAmountChange = { product2Amount = it },
            unit = product2Unit,
            onUnitChange = { product2Unit = it }
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ProductCard(
    title: String,
    name: String,
    onNameChange: (String) -> Unit,
    price: String,
    onPriceChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    unit: String,
    onUnitChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(R.string.name_label))
                },
                supportingText = {
                    Text(text = stringResource(R.string.name_help))
                },
                singleLine = true
            )

            OutlinedTextField(
                value = price,
                onValueChange = onPriceChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(R.string.price_label))
                },
                supportingText = {
                    Text(text = stringResource(R.string.price_help))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            OutlinedTextField(
                value = amount,
                onValueChange = onAmountChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(R.string.amount_label))
                },
                supportingText = {
                    Text(text = stringResource(R.string.amount_help))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Text(
                text = stringResource(R.string.unit_label),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            UnitGroupLabel(text = stringResource(R.string.group_weight))
            UnitOption(label = stringResource(R.string.mg), value = "mg", selectedValue = unit, onSelected = onUnitChange)
            UnitOption(label = stringResource(R.string.gram), value = "g", selectedValue = unit, onSelected = onUnitChange)
            UnitOption(label = stringResource(R.string.kg), value = "kg", selectedValue = unit, onSelected = onUnitChange)

            UnitGroupLabel(text = stringResource(R.string.group_volume))
            UnitOption(label = stringResource(R.string.ml), value = "ml", selectedValue = unit, onSelected = onUnitChange)
            UnitOption(label = stringResource(R.string.liter), value = "l", selectedValue = unit, onSelected = onUnitChange)

            UnitGroupLabel(text = stringResource(R.string.group_count))
            UnitOption(label = stringResource(R.string.pcs), value = "pcs", selectedValue = unit, onSelected = onUnitChange)
        }
    }
}

@Composable
private fun UnitGroupLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun UnitOption(
    label: String,
    value: String,
    selectedValue: String,
    onSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selectedValue == value,
                onClick = { onSelected(value) },
                role = Role.RadioButton
            )
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = selectedValue == value,
            onClick = null
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    KalkulatorNilaiProdukTheme {
        MainScreen()
    }
}
