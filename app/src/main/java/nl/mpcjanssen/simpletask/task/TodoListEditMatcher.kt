package nl.mpcjanssen.simpletask.task

internal object TodoListEditMatcher {
    fun findEditableTaskIndex(todoItems: List<Task>, original: Task): Int {
        val byObject = todoItems.indexOf(original)
        if (byObject != -1) {
            return byObject
        }

        val originalUuid = original.uuid
        if (originalUuid != null) {
            val byUuid = todoItems.indexOfFirst { it.uuid == originalUuid }
            if (byUuid != -1) {
                return byUuid
            }
        }

        val originalText = original.text
        val byExactText = todoItems.indexOfFirst { it.text == originalText }
        if (byExactText != -1) {
            return byExactText
        }

        val originalTextWithoutUuid = textWithoutUuid(original)
        return todoItems.indexOfFirst { textWithoutUuid(it) == originalTextWithoutUuid }
    }

    private fun textWithoutUuid(task: Task): String {
        return task.tokens
            .filterNot { it is UUIDToken }
            .joinToString(" ") { it.text }
    }
}
