import { PagingResults } from "../api";
import Axios from "axios";
import { Config } from "../config";
import { CloudProviderEntity } from "./CloudProviderEntity";
import { prepLangStrings } from "../util/langstrings";

export const GET_CLOUD_PROVIDER_LIST_URL = `${Config.baseUrl}api/cloudprovider`;
export const DELETE_CLOUD_PROVIDER_URL = `${
  Config.baseUrl
}api/cloudprovider/provider`;
export const POST_CLOUD_PROVIDER_REGISTER_INIT_URL = `${
  Config.baseUrl
}api/cloudprovider/register/init`;
export const langStrings = prepLangStrings("cp", {
  title: "Cloud providers",
  cloudProviderAvailable: {
    zero: "No cloud providers available",
    one: "%d cloud provider",
    more: "%d cloud providers"
  },
  newCloudProviderTitle: "Create cloud providers",
  newCloudProviderInfo: {
    id: "new_cloud_provider_url",
    label: "URL",
    help: "Cloud provider URL, e.g. www.equella.com/upload"
  }
});
interface CloudProviderInitResponse {
  url: string;
}
interface CloudProviderDeleteResponse {
  msg: string;
}

export function getCloudProviders(): Promise<
  PagingResults<CloudProviderEntity>
> {
  return Axios.get<PagingResults<CloudProviderEntity>>(
    GET_CLOUD_PROVIDER_LIST_URL
  ).then(res => res.data);
}

export function deleteCloudProvider(
  cloudProviderId: string
): Promise<CloudProviderDeleteResponse> {
  //let params = {cloudProviderId : cloudProviderId}
  return Axios.delete(DELETE_CLOUD_PROVIDER_URL + "/" + cloudProviderId).then(
    res => res.data
  );
}

export function registerCloudProviderInit(
  cloudProviderUrl: string
): Promise<CloudProviderInitResponse> {
  let params = {
    url: cloudProviderUrl
  };
  return Axios.post<CloudProviderInitResponse>(
    POST_CLOUD_PROVIDER_REGISTER_INIT_URL,
    null,
    {
      params: params
    }
  ).then(res => res.data);
}
