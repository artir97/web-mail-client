package mni.thm.de.webmailclient.storage

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.util.UUID

@Service
class FileStorageService {
    private val uploadDirectory: Path = Path.of("uploads")

    fun save(file: MultipartFile): String {
        require(!file.isEmpty) { "File must not be empty" }

        if (!Files.exists(uploadDirectory)) {
           Files.createDirectories(uploadDirectory)
        }

        val originalFileName = file.originalFilename ?: "attachment"
        val fileName = "${UUID.randomUUID()}_$originalFileName"
        val targetPath = uploadDirectory.resolve(fileName)

        file.inputStream.use {
            inputStream -> Files.copy(inputStream, targetPath)
        }

        return targetPath.toString()
    }
}
