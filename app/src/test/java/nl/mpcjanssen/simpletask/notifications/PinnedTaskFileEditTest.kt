package nl.mpcjanssen.simpletask.notifications

import junit.framework.TestCase

class PinnedTaskFileEditTest : TestCase() {
    fun testFindTaskByKeyAndTextReturnsMatchingPinnedTask() {
        val secondOccurrenceKey = PinnedTaskKey.from("/tmp/todo.txt", "Review release notes", occurrenceIndex = 1)

        val task = findPinnedTask(
            taskKey = secondOccurrenceKey,
            taskText = "Review release notes",
            tasks = listOf(
                nl.mpcjanssen.simpletask.task.Task("Review release notes"),
                nl.mpcjanssen.simpletask.task.Task("Review release notes"),
                nl.mpcjanssen.simpletask.task.Task("Plan sprint")
            )
        )

        assertNotNull(task)
        assertEquals("Review release notes", task!!.text)
        assertEquals(1, PinnedTaskKey.occurrenceIndex(secondOccurrenceKey))
    }

    fun testFindTaskInFileLinesReturnsMatchingPinnedTask() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "Review release notes"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "Review release notes",
            createdAt = 1L
        )

        val task = findPinnedTaskInFileLines(record, listOf("Plan sprint", "Review release notes"))

        assertNotNull(task)
        assertEquals("Review release notes", task!!.text)
    }

    fun testReplaceTaskInFileLinesUpdatesOnlyThePinnedTask() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "Review release notes"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "Review release notes",
            createdAt = 1L
        )

        val updatedLines = replacePinnedTaskInFileLines(
            record = record,
            fileLines = listOf("Plan sprint", "Review release notes"),
            updatedTaskText = "Review release notes +release"
        )

        assertEquals(listOf("Plan sprint", "Review release notes +release"), updatedLines)
    }

    fun testCompletePinnedTaskInFileLinesMarksTaskComplete() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "Review release notes"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "Review release notes",
            createdAt = 1L
        )

        val result = completePinnedTaskInFileLines(
            record = record,
            fileLines = listOf("Plan sprint", "Review release notes"),
            completedDate = "2026-06-09",
            useUUIDs = false,
            keepPriority = true,
            appendAtEnd = true,
            autoArchive = false
        )

        assertNotNull(result)
        assertEquals(listOf("Plan sprint", "x 2026-06-09 Review release notes"), result!!.todoLines)
        assertTrue(result.doneLines.isEmpty())
    }

    fun testCompletePinnedTaskInFileLinesReturnsNullWhenTaskMissing() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "Missing task"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "Missing task",
            createdAt = 1L
        )

        val result = completePinnedTaskInFileLines(
            record = record,
            fileLines = listOf("Plan sprint", "Review release notes"),
            completedDate = "2026-06-09",
            useUUIDs = false,
            keepPriority = true,
            appendAtEnd = true,
            autoArchive = false
        )

        assertNull(result)
    }

    fun testCompletePinnedTaskInFileLinesArchivesWhenAutoArchiveEnabled() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "Review release notes"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "Review release notes",
            createdAt = 1L
        )

        val result = completePinnedTaskInFileLines(
            record = record,
            fileLines = listOf("Plan sprint", "Review release notes"),
            completedDate = "2026-06-09",
            useUUIDs = false,
            keepPriority = true,
            appendAtEnd = true,
            autoArchive = true
        )

        assertNotNull(result)
        assertEquals(listOf("Plan sprint"), result!!.todoLines)
        assertEquals(listOf("x 2026-06-09 Review release notes"), result.doneLines)
    }
}
