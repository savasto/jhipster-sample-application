import { IUser } from 'app/shared/model/user.model';

export interface IVideo {
  id?: number;
  url?: string | null;
  description?: string | null;
  title?: string | null;
  videoSize?: number | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IVideo> = {};
