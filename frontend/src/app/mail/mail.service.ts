import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { MailOutput } from "./mail.model";

@Injectable({
    providedIn: 'root'
})

export class MailService {
    private readonly http = inject(HttpClient);
    private readonly apiUrl = 'http://localhost:8080';

    getMails(userId: string) {
        return this.http.get<MailOutput[]>(
            `${this.apiUrl}/users/${userId}/mails`
        );
    }

    getMail(userId: string, mailId: string) {
        return this.http.get<MailOutput>(
            `${this.apiUrl}/users/${userId}/mails/${mailId}`
        );
    }
}