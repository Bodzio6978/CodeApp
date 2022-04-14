package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case

data class CodeUseCases(
    val getCodes:GetCodes,
    val getCodesFlow: GetCodesFlow,
    val getCodeById:GetCodeById,
    val insertCode: InsertCode,
    val deleteCode: DeleteCode
)