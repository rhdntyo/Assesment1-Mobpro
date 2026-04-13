package com.RahadiyanAristyo0052.Assesment1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.material3.Button
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
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
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onOpenAbout: () -> Unit = {}) {
    var menuExpanded by rememberSaveable { mutableStateOf(false) }

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
                },
                actions = {
                    Box {
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = stringResource(R.string.menu_open)
                            )
                        }

                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(text = stringResource(R.string.menu_about))
                                },
                                onClick = {
                                    menuExpanded = false
                                    onOpenAbout()
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var product1Name by rememberSaveable { mutableStateOf("") }
    var product1Price by rememberSaveable { mutableStateOf("") }
    var product1Amount by rememberSaveable { mutableStateOf("") }
    var product1Unit by rememberSaveable { mutableStateOf("ml") }

    var product2Name by rememberSaveable { mutableStateOf("") }
    var product2Price by rememberSaveable { mutableStateOf("") }
    var product2Amount by rememberSaveable { mutableStateOf("") }
    var product2Unit by rememberSaveable { mutableStateOf("ml") }

    var hasCompared by rememberSaveable { mutableStateOf(false) }
    var showValidation by rememberSaveable { mutableStateOf(false) }
    var resultLine1 by rememberSaveable { mutableStateOf("") }
    var resultLine2 by rememberSaveable { mutableStateOf("") }
    var resultSummary by rememberSaveable { mutableStateOf("") }

    val product1Title = stringResource(R.string.product_1)
    val product2Title = stringResource(R.string.product_2)
    val product1PriceError = if (showValidation) validatePrice(product1Price) else null
    val product1AmountError = if (showValidation) validateAmount(product1Amount) else null
    val product2PriceError = if (showValidation) validatePrice(product2Price) else null
    val product2AmountError = if (showValidation) validateAmount(product2Amount) else null
    val canShare = resultLine1.isNotBlank() && resultLine2.isNotBlank() && resultSummary.isNotBlank()

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
            onUnitChange = { product1Unit = it },
            priceError = product1PriceError?.let { stringResource(it) },
            amountError = product1AmountError?.let { stringResource(it) }
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
            onUnitChange = { product2Unit = it },
            priceError = product2PriceError?.let { stringResource(it) },
            amountError = product2AmountError?.let { stringResource(it) }
        )

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                hasCompared = true
                showValidation = true
                val result = compareProducts(
                    context = context,
                    product1Label = product1Name.ifBlank { product1Title },
                    product1PriceText = product1Price,
                    product1AmountText = product1Amount,
                    product1Unit = product1Unit,
                    product2Label = product2Name.ifBlank { product2Title },
                    product2PriceText = product2Price,
                    product2AmountText = product2Amount,
                    product2Unit = product2Unit
                )

                resultLine1 = result.line1
                resultLine2 = result.line2
                resultSummary = result.summary
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.compare_button))
        }

        Spacer(modifier = Modifier.height(14.dp))

        ResultCard(
            hasCompared = hasCompared,
            resultLine1 = resultLine1,
            resultLine2 = resultLine2,
            resultSummary = resultSummary
        )

        if (canShare) {
            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = {
                    val shareText = context.getString(
                        R.string.share_text,
                        resultLine1,
                        resultLine2,
                        resultSummary
                    )

                    shareResult(context, shareText)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.share_button))
            }
        }

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
    onUnitChange: (String) -> Unit,
    priceError: String?,
    amountError: String?
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
                isError = priceError != null,
                label = {
                    Text(text = stringResource(R.string.price_label))
                },
                supportingText = {
                    Text(text = priceError ?: stringResource(R.string.price_help))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            OutlinedTextField(
                value = amount,
                onValueChange = onAmountChange,
                modifier = Modifier.fillMaxWidth(),
                isError = amountError != null,
                label = {
                    Text(text = stringResource(R.string.amount_label))
                },
                supportingText = {
                    Text(text = amountError ?: stringResource(R.string.amount_help))
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
fun ResultCard(
    hasCompared: Boolean,
    resultLine1: String,
    resultLine2: String,
    resultSummary: String
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(R.string.result_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (!hasCompared) {
                Text(
                    text = stringResource(R.string.result_start),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                return@Column
            }

            if (resultLine1.isNotBlank()) {
                Text(
                    text = resultLine1,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (resultLine2.isNotBlank()) {
                Text(
                    text = resultLine2,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = resultSummary,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
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

data class ComparisonResult(
    val line1: String,
    val line2: String,
    val summary: String
)

fun compareProducts(
    context: Context,
    product1Label: String,
    product1PriceText: String,
    product1AmountText: String,
    product1Unit: String,
    product2Label: String,
    product2PriceText: String,
    product2AmountText: String,
    product2Unit: String
): ComparisonResult {
    if (
        product1PriceText.isBlank() ||
        product1AmountText.isBlank() ||
        product2PriceText.isBlank() ||
        product2AmountText.isBlank()
    ) {
        return ComparisonResult(
            line1 = "",
            line2 = "",
            summary = context.getString(R.string.result_empty)
        )
    }

    if (unitCategory(product1Unit) != unitCategory(product2Unit)) {
        return ComparisonResult(
            line1 = "",
            line2 = "",
            summary = context.getString(R.string.result_incompatible)
        )
    }

    val product1Price = parsePrice(product1PriceText)
    val product1Amount = parseAmount(product1AmountText)
    val product2Price = parsePrice(product2PriceText)
    val product2Amount = parseAmount(product2AmountText)

    val product1UnitPrice = calculateUnitPrice(product1Price, product1Amount, product1Unit)
    val product2UnitPrice = calculateUnitPrice(product2Price, product2Amount, product2Unit)

    if (product1UnitPrice == null || product2UnitPrice == null) {
        return ComparisonResult(
            line1 = "",
            line2 = "",
            summary = context.getString(R.string.result_invalid)
        )
    }

    val unitLabel = baseUnitLabel(product1Unit)
    val line1 = context.getString(
        R.string.result_unit_price,
        product1Label,
        formatNumber(product1UnitPrice),
        unitLabel
    )
    val line2 = context.getString(
        R.string.result_unit_price,
        product2Label,
        formatNumber(product2UnitPrice),
        unitLabel
    )

    val summary = if (abs(product1UnitPrice - product2UnitPrice) < 0.0001) {
        context.getString(R.string.result_equal)
    } else if (product1UnitPrice < product2UnitPrice) {
        context.getString(R.string.result_better, product1Label)
    } else {
        context.getString(R.string.result_better, product2Label)
    }

    return ComparisonResult(
        line1 = line1,
        line2 = line2,
        summary = summary
    )
}

fun calculateUnitPrice(price: Double?, amount: Double?, unit: String): Double? {
    val baseAmount = amount?.let { convertToBaseUnit(it, unit) }

    if (price == null || amount == null || baseAmount == null) {
        return null
    }

    if (price <= 0.0 || amount <= 0.0 || baseAmount <= 0.0) {
        return null
    }

    return price / baseAmount
}

fun parsePrice(text: String): Double? {
    val cleanText = text.trim().replace(".", "").replace(",", "")
    return cleanText.toDoubleOrNull()
}

fun parseAmount(text: String): Double? {
    val cleanText = text.trim().replace(" ", "")

    if (cleanText.isEmpty()) {
        return null
    }

    if (cleanText.contains(",") && cleanText.contains(".")) {
        val lastComma = cleanText.lastIndexOf(",")
        val lastDot = cleanText.lastIndexOf(".")

        return if (lastComma > lastDot) {
            cleanText.replace(".", "").replace(",", ".").toDoubleOrNull()
        } else {
            cleanText.replace(",", "").toDoubleOrNull()
        }
    }

    if (cleanText.contains(",")) {
        return cleanText.replace(",", ".").toDoubleOrNull()
    }

    return cleanText.toDoubleOrNull()
}

fun convertToBaseUnit(amount: Double, unit: String): Double? {
    return when (unit) {
        "mg" -> amount / 1000
        "g" -> amount
        "kg" -> amount * 1000
        "ml" -> amount
        "l" -> amount * 1000
        "pcs" -> amount
        else -> null
    }
}

fun unitCategory(unit: String): String {
    return when (unit) {
        "mg", "g", "kg" -> "weight"
        "ml", "l" -> "volume"
        "pcs" -> "count"
        else -> ""
    }
}

fun baseUnitLabel(unit: String): String {
    return when (unit) {
        "mg", "g", "kg" -> "g"
        "ml", "l" -> "mL"
        "pcs" -> "pcs"
        else -> ""
    }
}

fun formatNumber(value: Double): String {
    val formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("id-ID"))
    formatter.minimumFractionDigits = 0
    formatter.maximumFractionDigits = 2
    return formatter.format(value)
}

fun validatePrice(text: String): Int? {
    val price = parsePrice(text)

    if (text.isBlank()) {
        return R.string.field_required
    }

    if (price == null || price <= 0.0) {
        return R.string.price_invalid_field
    }

    return null
}

fun validateAmount(text: String): Int? {
    val amount = parseAmount(text)

    if (text.isBlank()) {
        return R.string.field_required
    }

    if (amount == null || amount <= 0.0) {
        return R.string.amount_invalid_field
    }

    return null
}

fun shareResult(context: Context, text: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }

    context.startActivity(
        Intent.createChooser(
            shareIntent,
            context.getString(R.string.share_title)
        )
    )
}
