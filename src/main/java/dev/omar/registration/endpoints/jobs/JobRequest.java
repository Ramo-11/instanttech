package dev.omar.registration.endpoints.jobs;

import java.util.Date;

public record JobRequest(String title, String description, String rate, Date date) {
}
