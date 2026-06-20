export interface AttachmentOutput {
    id: string;
    fileName: string;
    contentType: string;
    size: number;
}

export interface MailOutput {
    id: string;
    sender: string;
    to: string;
    cc: string;
    bcc: string;
    subject: string;
    body: string;
    status: 'DRAFT' | 'SENT' | 'RECEIVED';
    createdAt: string;
    sentAt: string | null;
    attachments: AttachmentOutput[];
}