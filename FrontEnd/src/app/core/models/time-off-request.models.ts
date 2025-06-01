export interface CreateTimeOffRequestPayload {
  userEmail: string;
  start: string;
  end: string;
  leaveType: string;
  description: string | null;
  requestedDays: number;
}

export interface TimeOffRequestResponsePayload {
  id?: number;
  description: string | null;
  start: string;
  end: string;
  type: number;
  status: number;
  user: string;
  requestUserNumber: number;
}
