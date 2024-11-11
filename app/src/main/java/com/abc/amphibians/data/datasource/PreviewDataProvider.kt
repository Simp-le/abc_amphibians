package com.abc.amphibians.data.datasource

import com.abc.amphibians.data.model.Amphibian

object PreviewDataProvider {
    val amphibian = Amphibian(
        name = "Lorem Ipsum",
        type = "(Lorem)",
        photoUrl = "",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In id mi ut purus condimentum sollicitudin nec fermentum tellus. In non metus eleifend, varius massa vel, vestibulum nisl. Morbi eget ornare quam. Fusce vel ultricies mi, et consequat odio. Cras interdum vehicula mauris, sit amet placerat diam finibus sit amet. Donec imperdiet libero eget metus convallis, a pharetra est rutrum. Integer in felis eu lacus consequat pretium."
    )

    fun getAmphibianList(amount: Int = 10): List<Amphibian> {
        return List(amount) { amphibian }
    }
}