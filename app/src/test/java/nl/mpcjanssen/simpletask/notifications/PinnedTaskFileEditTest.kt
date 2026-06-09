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

    fun testFindTaskInFileLinesMatchesTaskWhenFileHasGeneratedUuid() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "QA pin now"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "QA pin now",
            createdAt = 1L
        )

        val task = findPinnedTaskInFileLines(
            record,
            listOf(
                "QA pin later uuid:22222222-2222-2222-2222-222222222222",
                "QA pin now uuid:11111111-1111-1111-1111-111111111111"
            )
        )

        assertNotNull(task)
        assertEquals("QA pin now uuid:11111111-1111-1111-1111-111111111111", task!!.text)
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

    fun testCompletePinnedTaskInFileLinesMarksTaskCompleteWhenFileHasGeneratedUuid() {
        val record = PinnedTaskRecord(
            taskKey = PinnedTaskKey.from("/tmp/todo.txt", "QA pin now"),
            todoFilePath = "/tmp/todo.txt",
            taskText = "QA pin now",
            createdAt = 1L
        )

        val result = completePinnedTaskInFileLines(
            record = record,
            fileLines = listOf(
                "QA pin later uuid:22222222-2222-2222-2222-222222222222",
                "QA pin now uuid:11111111-1111-1111-1111-111111111111"
            ),
            completedDate = "2026-06-09",
            useUUIDs = true,
            keepPriority = true,
            appendAtEnd = true,
            autoArchive = false
        )

        assertNotNull(result)
        assertEquals(
            listOf(
                "QA pin later uuid:22222222-2222-2222-2222-222222222222",
                "x 2026-06-09 QA pin now uuid:11111111-1111-1111-1111-111111111111"
            ),
            result!!.todoLines
        )
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
