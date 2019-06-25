package c.gingdev.allnewdlab.models

data class ItemModel (val template: template)
data class template(val outputs: List<outputs>)
data class outputs(val simpleText: simpleText)
data class simpleText(val text: String)