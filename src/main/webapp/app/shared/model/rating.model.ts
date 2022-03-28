import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { IVideo } from 'app/shared/model/video.model';

export interface IRating {
  id?: number;
  rating?: string | null;
  date?: string | null;
  user?: IUser | null;
  video?: IVideo | null;
}

export const defaultValue: Readonly<IRating> = {};
