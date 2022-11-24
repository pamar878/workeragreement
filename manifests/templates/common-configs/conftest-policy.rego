package tigo

import data.kubernetes

name = input.metadata.name

deny[msg] {
    input.kind = "Deployment"
    not input.spec.template.spec.securityContext.runAsNonRoot
    msg = sprintf("Containers must not run as root in Deployment %s", [name])
}

required_deployment_labels {
    input.metadata.labels["app.kubernetes.io/name"]
    input.metadata.labels["app.kubernetes.io/version"]
}

deny[msg] {
  container := kubernetes.containers[_]
  endswith(container.image, ":latest")
  msg := sprintf("container '%s' is using the latest tag for its image (%s), which is an anti-pattern.", [container.name, container.image])
}

deny[msg] {
  container := kubernetes.containers[_]
  not container.readinessProbe
  msg := sprintf("container '%s' has no readinessProbe", [container.name, container.image])
}

deny[msg] {
  container := kubernetes.containers[_]
  not container.livenessProbe
  msg := sprintf("container '%s' has no livenessProbe", [container.name, container.image])
}

deny[msg] {
	kubernetes.containers[container]
	not container.resources.limits.memory
	msg = sprintf("%s in the %s %s does not have a memory limit set", [container.name, kubernetes.kind, kubernetes.name])
}

deny [msg] {
    input.kind = "Deployment"
    not required_deployment_labels
    msg = sprintf("Deployment %s must provide required labels: name, version", [name])
}
