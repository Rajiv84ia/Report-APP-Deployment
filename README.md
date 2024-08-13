Project Setup Using Jenkins, Maven, GitHub, Docker, and Kubernetes
Step 1: Jenkins Server Setup on Linux VM
Create Ubuntu VM on AWS EC2:

Choose instance type t2.medium.
Open port 8080 in the Security Group's Inbound Rules.
Connect to VM:

Use MobaXterm or another SSH client to connect.
Install Java:

bash
Copy code
sudo apt update
sudo apt install fontconfig openjdk-17-jre
java -version
Install Jenkins:

bash
Copy code
sudo wget -O /usr/share/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins
Start Jenkins:

bash
Copy code
sudo systemctl enable jenkins
sudo systemctl start jenkins
sudo systemctl status jenkins
Verify Jenkins:

Open Jenkins in your browser at http://<public-ip>:8080/.
Retrieve the admin password:
bash
Copy code
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
Create an admin account and install required plugins.
Step 2: Configure Maven in Jenkins
Navigate to Manage Jenkins -> Tools -> Maven Installation -> Add Maven and configure it.
Step 3: Setup Docker in Jenkins
Install Docker:

bash
Copy code
curl -fsSL get.docker.com | /bin/bash
Add Jenkins User to Docker Group:

bash
Copy code
sudo usermod -aG docker jenkins
sudo systemctl restart jenkins
sudo docker version
Step 4: Create EKS Management Host on AWS
Launch Ubuntu VM:

Choose instance type t2.micro.
Install kubectl:

bash
Copy code
curl -o kubectl https://amazon-eks.s3.us-west-2.amazonaws.com/1.19.6/2021-01-05/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin
kubectl version --short --client
Install AWS CLI:

bash
Copy code
sudo apt install unzip
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
aws --version
Install eksctl:

bash
Copy code
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
eksctl version
Step 5: Create IAM Role and Attach to EKS Management Host and Jenkins Server
Create IAM Role:

Use EC2 use case and attach the following policies:
IAM - FullAccess
VPC - FullAccess
EC2 - FullAccess
CloudFormation - FullAccess
Administrator - Access
Role Name: eksroleec2
Attach Role:

To EKS Management Host: Modify IAM Role in EC2 Security.
To Jenkins Server: Modify IAM Role in EC2 Security.
Step 6: Create EKS Cluster Using eksctl
bash
Copy code
eksctl create cluster --name <cluster-name> --region <region-name> --node-type <instance-type> --nodes-min 2 --nodes-max 2 --zones <zones>
Example:

bash
Copy code
eksctl create cluster --name ashokit-cluster --region ap-south-1 --node-type t2.medium --zones ap-south-1a,ap-south-1b
Note: Cluster creation takes about 5 to 10 minutes. Check nodes with:
bash
Copy code
kubectl get nodes
Step 7: Install AWS CLI on Jenkins Server
Install AWS CLI:
bash
Copy code
sudo apt install unzip
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
aws --version
Step 8: Install kubectl on Jenkins Server
Install kubectl:
bash
Copy code
curl -o kubectl https://amazon-eks.s3.us-west-2.amazonaws.com/1.19.6/2021-01-05/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin
kubectl version --short --client
Step 9: Update EKS Cluster Config on Jenkins Server
Copy kubeconfig:

From EKS Management Host:
bash
Copy code
cat ~/.kube/config
Add kubeconfig to Jenkins Server:

bash
Copy code
cd /var/lib/jenkins
sudo mkdir .kube
sudo vi .kube/config
Verify Cluster Access:

bash
Copy code
kubectl get nodes
Step 10: Create Jenkins CI Job
Stages:
Clone Git Repo
Build
Create Docker Image
Push Docker Image to Registry
Trigger CD Job
Step 11: Create Jenkins CD Job
Stages:
Clone Kubernetes Manifest Files
Deploy App in Kubernetes EKS Cluster
Send Confirmation Email
Step 12: Trigger Jenkins CI Job
The CI Job executes all stages and triggers the CD Job, which fetches the Docker image and deploys it to the cluster.
Step 13: Access Application in Browser
Access your application at http://<LBR>/context-path/.
Step 14: Clean Up
After practice, delete the EKS cluster and other AWS resources to avoid unnecessary charges.
