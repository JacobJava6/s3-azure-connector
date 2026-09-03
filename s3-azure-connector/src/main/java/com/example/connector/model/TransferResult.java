package com.example.connector.model;

import java.util.List;

public record TransferResult(
        int total,
        int successful,
        int failed,
        List<String> successfulFiles,
        List<String> failedFiles
) {
}